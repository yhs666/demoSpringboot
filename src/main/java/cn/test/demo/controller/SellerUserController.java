package cn.test.demo.controller; /*
 * @author: Max Yang
 * @date: 2021-02-26 5:20
 * @desc: 卖家用户
 */

import cn.test.demo.config.ProjectUrlConfig;
import cn.test.demo.constant.CookieConstant;
import cn.test.demo.constant.RedisConstant;
import cn.test.demo.dataobject.SellerInfo;
import cn.test.demo.enums.ResoultEnum;
import cn.test.demo.servie.SellerService;
import cn.test.demo.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController{
    @Autowired
    private SellerService  sellerService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    @GetMapping("/login")
    public ModelAndView login(
            @RequestParam("openid") String openid,
            HttpServletResponse response,
            Map<String,Object> map
    ){
        // 1  openid 去和数据库匹配
        SellerInfo  sellerInfo= sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo == null){
            map.put("msg", ResoultEnum.LOGIN_ERROR.getMessage());
            map.put("url","/sell/seller/order/list");
            return  new ModelAndView("common/error",map);
        }
        // 2 设置token 去 redis
        String token= UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire, TimeUnit.SECONDS);
        log.info(token);
        // 3 设置token to cookies
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);

        //

        return  new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");
    }
}
