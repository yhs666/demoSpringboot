package cn.test.demo.config; /*
 * @author: Max Yang
 * @date: 2021-02-02 2:33
 * @desc:
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    // 公众号 app ID
    private  String mpAppId;
    // 公众号 app secret
    private  String mpAppSecret;

    // 开放 app ID
    private  String openAppId;
    // 开放平台 app secret
    private  String openAppSecret;

    // 商户号
    private String mchId;
    // 商户秘钥
    private String mchKey;
    // 商户证书路径
    private String keyPath;
    // 微信支付成功通知地址
    private  String notifyUrl;
}
