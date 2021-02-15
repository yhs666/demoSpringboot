package cn.test.demo.dataobject; /*
 * @author: Max Yang
 * @date: 2021-01-31 6:03
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
public class OrderDetail {

    //id
    @Id
    private  String detailId;

    //  订单id
    private String orderId;
    //  商品名 id
    private String productId;
    //  商品 名称
    private String productName;
    // 商品 单价
    private BigDecimal productPrice;

    // 商品 数量
    private  Integer productQuantity;
    // 商品 小图
    private String productIcon;

}
