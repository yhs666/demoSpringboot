package cn.test.demo.utils; /*
 * @author: Max Yang
 * @date: 2021-01-31 9:32
 * @desc:
 */

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式： 时间 + 随机数
     * **/
    public  static synchronized String  genUniqueKey(){
        Random random = new Random();

        Integer number = random.nextInt(9000000) + 1000000;
        return  System.currentTimeMillis() + String.valueOf(number);
    }
}
