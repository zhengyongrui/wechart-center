package com.zyr.wechat.setting.config.service;

import com.zyr.wechat.miniapp.config.WxMaProperties;
import com.zyr.wechat.setting.config.domain.po.WechatConfig;

import java.util.List;

/**
 * @Author: zhengyongrui
 * @Date: 2020-03-05 00:31
 */
public interface IWechatConfigService {

    /**
     * 同步配置文件的微信配置并查找全部的微信配置
     * @param propertiesConfigList 配置文件的微信配置
     * @return .
     */
    List<WechatConfig> syncAndFindAllWechatConfig(List<WxMaProperties.Config> propertiesConfigList);

    WechatConfig findByAppid(String appid);
}
