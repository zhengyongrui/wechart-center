package com.zyr.wechat.setting.config.service.impl;

import com.zyr.common.util.beans.CommonBeanUtil;
import com.zyr.wechat.miniapp.config.WxMaProperties;
import com.zyr.wechat.setting.config.domain.po.WechatConfig;
import com.zyr.wechat.setting.config.service.IWechatConfigService;
import com.zyr.wechat.setting.config.dao.IWechatConfigDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: zhengyongrui
 * @Date: 2020-03-05 00:04
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class WechatConfigService implements IWechatConfigService {

    private final IWechatConfigDao wechatConfigDao;

    @Override
    public List<WechatConfig> syncAndFindAllWechatConfig(List<WxMaProperties.Config> propertiesConfigList) {
        List<WechatConfig> resultList = wechatConfigDao.findAll();
        Stream<String> appidStream = resultList.stream().map(WechatConfig::getAppid);
        List<WechatConfig> newWechatConfigList = propertiesConfigList.stream()
                .filter(propertiesConfig -> appidStream.noneMatch(appid -> appid.equals(propertiesConfig.getAppid())))
                .map(propertiesConfig -> CommonBeanUtil.copyBean(propertiesConfig, WechatConfig.class))
                .collect(Collectors.toList());
        wechatConfigDao.saveAll(newWechatConfigList);
        resultList.addAll(newWechatConfigList);
        return resultList;
    }

    @Override
    public WechatConfig findByAppid(String appid) {
        return wechatConfigDao.findById(appid).orElse(null);
    }
}
