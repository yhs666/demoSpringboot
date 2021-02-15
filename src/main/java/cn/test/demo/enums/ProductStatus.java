package cn.test.demo.enums; /*
 * @author: Max Yang
 * @date: 2021-01-30 7:13
 * @desc:
 */

import lombok.Getter;

// 商品状态
@Getter
public enum  ProductStatus {
    Up(0,"在架"),
    DOWN(1,"下架")
    ;
    private  Integer code;
    private  String  message;

    ProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
