package com.zyr.wechat.user.domain.dto;

import lombok.Data;

/**
 * @Author: zhengyongrui
 * @Date: 2020-03-04 22:33
 */
@Data
public class WechatUserDto {

    /**
     * 设置微信小程序的appid
     */
    private String appid;

    /**
     * 设置微信小程序的Secret
     */
    private String secret;

    /**
     * 设置微信小程序消息服务器配置的token
     */
    private String token;

    /**
     * 设置微信小程序消息服务器配置的EncodingAESKey
     */
    private String aesKey;

    /**
     * 消息格式，XML或者JSON
     */
    private String msgDataFormat;

}
