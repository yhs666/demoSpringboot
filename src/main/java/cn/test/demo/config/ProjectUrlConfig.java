package cn.test.demo.config; /*
 * @author: Max Yang
 * @date: 2021-02-24 10:25
 * @desc:
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "projecturl")
public class ProjectUrlConfig {
    /*
    微信公众平台授权 url
     */
    public  String wechatMpAuthorize;
    /**
     * 微信开放平台授权url
     */
    public  String wechatOpenAuthorize;
    /**
     * 点餐 系统
     */
    public  String sell;

}
