package com.zyr.wechat.user.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.zyr.common.util.beans.CommonBeanUtil;
import com.zyr.common.util.jwt.JwtOperator;
import com.zyr.wechat.miniapp.config.WxMaConfiguration;
import com.zyr.wechat.user.dao.IWechatUserDao;
import com.zyr.wechat.user.domain.po.WechatUser;
import com.zyr.wechat.user.domain.vo.WechatLoginResultVo;
import com.zyr.wechat.user.domain.vo.WechatUserVo;
import com.zyr.wechat.user.service.IWechatUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhengyongrui
 * @Date: 2020-03-05 00:04
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
public class WechatUserService implements IWechatUserService {

    private final IWechatUserDao wechatUserDao;

    private final JwtOperator jwtOperator;

    @Override
    public WechatLoginResultVo login(String appid, String code) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            WechatUser wechatUser = syncAndFindWechatUser(session);
            WechatUserVo wechatUserVo = CommonBeanUtil.copyBean(wechatUser, WechatUserVo.class);
            Map<String, Object> map = new HashMap<>(16);
            map.put("wechatUser", wechatUserVo);
            String token = jwtOperator.generateToken(map);
            WechatLoginResultVo wechatLoginResultVo = WechatLoginResultVo.builder().token(token).wechatUserVo(wechatUserVo).build();
            log.debug(
                    "用户{}登录成功，生成的token = {}, 有效期到:{}",
                    wechatUserVo.getOpenId(),
                    token,
                    jwtOperator.getExpirationTime()
            );
            return wechatLoginResultVo;
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 同步和查询微信用户信息
     * 如果没找到对应openid记录则更新同步到数据库
     *
     * @param wxMaJscode2SessionResult
     * @return
     */
    private WechatUser syncAndFindWechatUser(WxMaJscode2SessionResult wxMaJscode2SessionResult) {
        final String openid = wxMaJscode2SessionResult.getOpenid();
        return wechatUserDao.findById(openid).orElseGet(() -> {
            // todo 后面userId去账户中心新建获取
            WechatUser wechatUserForSave = WechatUser.builder()
                    .openId(openid)
                    .unionId(wxMaJscode2SessionResult.getUnionid())
                    .userId(openid)
                    .createDate(System.currentTimeMillis())
                    .build();
            wechatUserDao.save(wechatUserForSave);
            return wechatUserForSave;
        });
    }
}
