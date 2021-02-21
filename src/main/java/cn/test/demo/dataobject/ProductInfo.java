package cn.test.demo.dataobject;

/*
 * @author: Max Yang
 * @date: 2021-01-30 6:27
 * @desc:
 */

import cn.test.demo.enums.OrderStatusEnum;
import cn.test.demo.enums.ProductStatus;
import cn.test.demo.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
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
    private  Integer productStatus = ProductStatus.Up.getCode();

    // 商品类目编号
    private  Integer categoryType;

    // 创建时间
    @CreatedDate
    private Date createTime;
    // 创建时间
    @LastModifiedDate
    private  Date updateTime;

    @JsonIgnore
    public ProductStatus getProductStatusEnum(){
        return EnumUtil.getBycode(productStatus,ProductStatus.class);
    }

}
