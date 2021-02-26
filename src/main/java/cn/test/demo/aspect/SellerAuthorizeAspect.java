package cn.test.demo.aspect; /*
 * @author: Max Yang
 * @date: 2021-02-26 10:43
 * @desc:
 */

import cn.test.demo.constant.CookieConstant;
import cn.test.demo.constant.RedisConstant;
import cn.test.demo.exception.SellException;
import cn.test.demo.exception.SellerAuthorizeException;
import cn.test.demo.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public  * cn.test.demo.controller.Seller*.*(..)) && !execution(public  * cn.test.demo.controller.SellerUserController.*(..))")
    public  void  verify(){

    }

    public  void  doVerify(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = servletRequestAttributes.getRequest();
        // get cookies
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie == null){
            log.warn(" [登录校验] Cookie 中查不到token");
            throw  new SellerAuthorizeException();
        }
        // go to redis check token
        String openid = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        if(StringUtils.hasLength(openid)){
            log.warn("[登录校验] Redis 中查不到token");
            throw new SellerAuthorizeException();
        }
    }
}
