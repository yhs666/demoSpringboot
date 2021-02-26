package cn.test.demo.utils; /*
 * @author: Max Yang
 * @date: 2021-02-26 8:46
 * @desc: cookie tool
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    /**
     *  设置 cookies
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public  static  void  set(HttpServletResponse response,
                              String name,
                              String value,
                              int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public  static  void  get(){

    }
}
