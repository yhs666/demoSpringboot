package cn.test.demo.config; /*
 * @author: Max Yang
 * @date: 2021-02-24 9:49
 * @desc:
 */

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.config.impl.WxMpRedissonConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatOpenConfig {
    @Autowired
    private  WechatAccountConfig accountConfig;

    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
        return  wxOpenService;
    }
    //@Bean
    public WxMpConfigStorage wxOpenConfigStorage(){
        WxMpDefaultConfigImpl wxMpConfigStorage = new WxMpDefaultConfigImpl();
        wxMpConfigStorage.setAppId(accountConfig.getOpenAppId());
        wxMpConfigStorage.setSecret(accountConfig.getOpenAppSecret());
        return  wxMpConfigStorage;
    }
}
