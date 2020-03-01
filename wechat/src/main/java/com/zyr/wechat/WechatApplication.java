package com.zyr.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: zhengyongrui
 * @Date: 2020-03-01 10:40
 */
@SpringBootApplication
@ComponentScan("com.zyr")
public class WechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
    }

}
