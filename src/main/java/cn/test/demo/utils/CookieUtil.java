package cn.test.demo.utils; /*
 * @author: Max Yang
 * @date: 2021-02-26 8:46
 * @desc: cookie tool
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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

    /**
     *  通过Request 获取指定cookie
     * @param request
     * @param name
     * @return
     */
    public  static  Cookie  get(HttpServletRequest request,
                              String name){

        Map<String,Cookie> cookieMap = readCookieMap(request);
        if(cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }else {
            return null;
        }

    }

    /**
     *  通过 Request 获取 用户请求所携带的cookie 信息
     * @param request
     * @return
     */
    private  static Map<String,Cookie> readCookieMap(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Map<String,Cookie> cookieMap = new HashMap<>();
        if(cookies !=null){
            for (Cookie c:cookies){
                cookieMap.put(c.getName(),c);
            }
        }
        return  cookieMap;
    }
}
