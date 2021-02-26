package cn.test.demo.constant; /*
 * @author: Max Yang
 * @date: 2021-02-26 8:14
 * @desc: redis 常量
 */

public interface RedisConstant {

    Integer EXPIRE =7200 ;  // 2 小时

    String TOKEN_PREFIX="token_%s";
}
