package com.ray.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     * 公众平台id
     */
    private String mpAppId;

    /**
     * 公众平台密钥
     */
    private String mpAppSecret;

    /**
     * 借用账号
     */
    private String mchId;

    /**
     * 秘钥
     */
    private String mchKey;

    /**
     * 秘钥路径
     */
    private String keyPath;
    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;
    /**
     * 开放平台id
     */
    private String openAppId;
    /**
     * 开放平台秘钥
     */
    private String openAppSecret;

    /**
     *微信模板id
     */
    private Map<String,String> templateId;
}
