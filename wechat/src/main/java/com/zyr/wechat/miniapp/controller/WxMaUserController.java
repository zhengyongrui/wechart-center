package com.zyr.wechat.miniapp.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.zyr.wechat.miniapp.config.WxMaConfiguration;
import com.zyr.wechat.user.domain.vo.WechatUserVo;
import com.zyr.wechat.user.service.IWechatUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序用户接口
 * @author zhengyongrui
 */
@RestController
@RequestMapping("/wx/user/{appid}")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WxMaUserController {

    private final IWechatUserService wechatUserService;

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public WechatUserVo login(@PathVariable String appid, @NonNull String code) {
        return wechatUserService.login(appid, code);
    }

    /**
     * 获取用户信息接口
     */
    @GetMapping("/info")
    public String info(
            @PathVariable String appid,
            String sessionKey,
            String signature,
            String rawData,
            String encryptedData,
            String iv
    ) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return userInfo.toString();
    }

    /**
     * 获取用户绑定手机号信息
     */
    @GetMapping("/phone")
    public String phone(@PathVariable String appid, String sessionKey, String signature,
                        String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return phoneNoInfo.toString();
    }

}
