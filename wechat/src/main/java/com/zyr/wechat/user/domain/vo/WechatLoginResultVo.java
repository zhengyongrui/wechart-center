package com.zyr.wechat.user.domain.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: zhengyongrui
 * @Date: 2020-03-08 00:13
 */
@Data
@Builder
public class WechatLoginResultVo {

    private WechatUserVo wechatUserVo;

    private String token;
}
