package cn.test.demo.utils; /*
 * @author: Max Yang
 * @date: 2021-02-15 6:26
 * @desc:
 */

import cn.test.demo.enums.CodeEnum;

public class EnumUtil {
    /**
     *   通过泛型 ，查询枚举类型， 根据code 去查
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public  static  <T extends CodeEnum> T getBycode(Integer code, Class<T> enumClass){

        for (T each: enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return  each;
            }
        }
        return  null;

    }
}
