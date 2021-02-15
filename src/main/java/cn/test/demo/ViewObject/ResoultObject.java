package cn.test.demo.ViewObject; /*
 * @author: Max Yang
 * @date: 2021-01-30 10:23
 * @desc:
 */
// http response object

import lombok.Data;

@Data
public class ResoultObject<T> {
    //  错误代码
    private  Integer code;
    // 提示信息
    private  String  msg;
    // 具体内容
    private  T data;

}
