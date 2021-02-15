package cn.test.demo.ViewObject;

/*
* 商品详情
 * @author: Max Yang
 * @date: 2021-01-30 10:53
 * @desc:
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoViewObject {
    @JsonProperty("id")
    private  String productId;
    @JsonProperty("name")
    private  String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private  String productDescription;
    @JsonProperty("icon")
    private  String productIcon;
}
