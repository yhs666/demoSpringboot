package cn.test.demo.dto;

/*
 * @author: Max Yang
 * @date: 2021-01-31 12:16
 * @desc: 购物车
 * 前端 提交 对象
 */

import lombok.Data;

@Data
public class CartDTO {

    // 购买 商品 ID
    private String productId;
    // 购买数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
