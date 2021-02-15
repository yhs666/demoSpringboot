package cn.test.demo.config; /*
 * @author: Max Yang
 * @date: 2021-02-02 2:23
 * @desc:
 */

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatMpConfig {

    @Autowired
    private  WechatAccountConfig accountConfig;
    @Bean
    public WxMpService  wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    //@Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpDefaultConfigImpl wxMpDefaultConfig= new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setSecret(accountConfig.getMpAppSecret());
        wxMpDefaultConfig.setAppId(accountConfig.getMpAppId());
        return  wxMpDefaultConfig;
    }
}
