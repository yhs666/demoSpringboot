package cn.test.demo.controller; /*
 * @author: Max Yang
 * @date: 2021-02-02 2:18
 * @desc:
 */

import cn.test.demo.enums.ResoultEnum;
import cn.test.demo.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;


//@RestController  // rest controll 不会重定向
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Autowired
    private  WxMpService wxMpService;
    @GetMapping("/authorize")
    public String authorize (@RequestParam("returnUrl") String returnUrl) throws  Exception{
        String url="http://sell.9test.cn/sell/wechat/userInfo";
        String redirectUrl=wxMpService.getOAuth2Service().buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl, "UTF-8"));
        log.info("[微信网页授权] 获取code，result={}",redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public  String  userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxOAuth2AccessToken wxOAuth2AccessToken = new WxOAuth2AccessToken();
        try {
            wxOAuth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(code);
        }catch (Exception e)
        {
            log.error("[微信网页授权] 获取token，result={}",e);
            throw  new SellException(ResoultEnum.WECHAT_MP_ERROR.getCode(),e.getMessage());
        }
        String opendId = wxOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid="+ opendId;
    }
}

/*
构造网页授权url
首先构造网页授权url，然后构成超链接让用户点击：

WxMpService wxMpService = ...;
String url = ...;
wxMpService.getOauth2Service().buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null)
获得access token
当用户同意授权后，会回调所设置的url并把authorization code传过来，然后用这个code获得access token，其中也包含用户的openid等信息

WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(code);
获得用户基本信息
WxMpUser wxMpUser = wxMpService.getOAuth2Service().getUserInfo(wxMpOAuth2AccessToken, null);
刷新access token
wxMpOAuth2AccessToken = wxMpService.getOAuth2Service().refreshAccessToken(wxMpOAuth2AccessToken.getRefreshToken());
验证access token
boolean valid = wxMpService.getOAuth2Service().validateAccessToken(wxMpOAuth2AccessToken);
*/
