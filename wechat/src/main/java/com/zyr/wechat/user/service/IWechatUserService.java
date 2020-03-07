package com.zyr.wechat.user.service;

import com.zyr.wechat.user.domain.vo.WechatLoginResultVo;

/**
 * @Author: zhengyongrui
 * @Date: 2020-03-05 00:31
 */
public interface IWechatUserService {

    WechatLoginResultVo login(String appid, String code);
}
