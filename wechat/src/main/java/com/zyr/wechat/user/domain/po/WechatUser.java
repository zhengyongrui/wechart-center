package com.zyr.wechat.user.domain.po;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Map;

/**
 * 微信用户信息
 *
 * @Author: zhengyongrui
 * @Date: 2020-03-04 22:33
 */
@Data
@Builder
public class WechatUser{

    @Id
    private String openId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像图片的 URL。URL 最后一个数值代表正方形头像大小（有 0、46、64、96、132 数值可选，0 代表 640x640 的正方形头像，46 表示 46x46 的正方形头像，剩余数值以此类推。默认132），用户没有头像时该项为空。若用户更换头像，原有头像 URL 将失效。
     */
    private String avatarUrl;

    /**
     * 用户性别
     */
    private String gender;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 显示 country，province，city 所用的语言
     */
    private String language;

    /**
     * 微信平台唯一id
     * 如果开发者拥有多个移动应用、网站应用、和公众帐号（包括小程序），可通过 UnionID 来区分用户的唯一性，因为只要是同一个微信开放平台帐号下的移动应用、网站应用和公众帐号（包括小程序），用户的 UnionID 是唯一的。换句话说，同一用户，对同一个微信开放平台下的不同应用，UnionID是相同的
     */
    private String unionId;

    /**
     * 账户中心对应的用户id
     */
    private String userId;

    /**
     * 创建时间
     */
    private long createDate;

}
