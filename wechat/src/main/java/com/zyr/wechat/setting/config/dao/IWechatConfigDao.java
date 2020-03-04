package com.zyr.wechat.setting.config.dao;


import com.zyr.common.repository.ProjectRepository;
import com.zyr.wechat.setting.config.domain.po.WechatConfig;

/**
 * @Author: zhengyongrui
 * @Date: 2020-03-04 23:57
 */
public interface IWechatConfigDao extends IWechatConfigRepository, ProjectRepository<WechatConfig, String> {
}
