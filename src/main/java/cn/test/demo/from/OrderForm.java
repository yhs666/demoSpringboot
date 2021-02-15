package cn.test.demo.from; /*
 * @author: Max Yang
 * @date: 2021-02-01 5:01
 * @desc:
 */

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;


@Data
public class OrderForm {

    @NotEmpty(message="姓名必填")
    private  String  name;
    @NotEmpty(message="手机号必填")
    private  String  phone;
    @NotEmpty(message="地址 必填")
    private  String address;
    @NotEmpty(message="openid 必填")
    private  String openid;
    @NotEmpty(message="购物车信息不能为空")
    private  String items;
}
