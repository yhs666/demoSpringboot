package cn.test.demo.enums; /*
 * @author: Max Yang
 * @date: 2021-01-31 5:48
 * @desc:
 */

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消"),
            ;
    private Integer code;
    private String  message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
