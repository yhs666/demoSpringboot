package cn.test.demo.dataobject; /*
 * @author: Max Yang
 * @date: 2021-01-31 5:42
 * @desc:
 */

import cn.test.demo.enums.OrderStatusEnum;
import cn.test.demo.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@DynamicUpdate
//@Proxy(lazy = false)
@EntityListeners(AuditingEntityListener.class)  // 处理mysql 8 自动生成时间问题
public class OrderMaster {
    // 订单 id
    @Id
    private String orderId;
    // 买家名字
    private  String buyerName;
    /** 买家手机号 **/
    private String buyerPhone;
    // 买家地址
    private String buyerAddress;
    // 买家微信open id
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    // 订单状态，默认为新下单 0
    private  Integer orderStatus= OrderStatusEnum.NEW.getCode();

    // 支付状态，默认未支付 0
    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    // 创建时间
    @CreatedDate
    private Date createTime;
    // 更新时间
    @LastModifiedDate
    private Date updateTime;

}
