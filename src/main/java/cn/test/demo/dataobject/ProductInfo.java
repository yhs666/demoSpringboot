package cn.test.demo.dataobject;

/*
 * @author: Max Yang
 * @date: 2021-01-30 6:27
 * @desc:
 */

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
@DynamicUpdate
public class ProductInfo {
    @Id
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

    // 0 正常 1 下架
    private  Integer productStatus;

    // 商品类目编号
    private  Integer categoryType;

}
