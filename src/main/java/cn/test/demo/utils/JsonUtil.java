package cn.test.demo.utils; /*
 * @author: Max Yang
 * @date: 2021-02-04 12:53
 * @desc:
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return  gson.toJson(object);
    }
}
