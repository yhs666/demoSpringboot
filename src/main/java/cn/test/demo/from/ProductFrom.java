package cn.test.demo.from; /*
 * @author: Max Yang
 * @date: 2021-02-21 5:28
 * @desc:
 */

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductFrom {
    private  String productId;
    // 产品名字
    private  String productName;

    // 产品单价
    private BigDecimal productPrice;
    // 库存
    private  Integer productStock;

    // 产品描述
    private  String productDescription;
    //链接地址
    private  String productIcon;


    // 商品类目编号
    // 商品类目编号
    private  Integer categoryType;

}
