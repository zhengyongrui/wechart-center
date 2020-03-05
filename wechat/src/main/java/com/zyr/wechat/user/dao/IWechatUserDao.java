package com.zyr.wechat.user.dao;


import com.zyr.common.repository.ProjectRepository;
import com.zyr.wechat.user.domain.po.WechatUser;

/**
 * @Author: zhengyongrui
 * @Date: 2020-03-04 23:57
 */
public interface IWechatUserDao extends IWechatUserRepository, ProjectRepository<WechatUser, String> {
}
