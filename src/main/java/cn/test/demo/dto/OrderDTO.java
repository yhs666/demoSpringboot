package cn.test.demo.dto; /*
 * @author: Max Yang
 * @date: 2021-01-31 8:52
 * @desc:
 */

import cn.test.demo.dataobject.OrderDetail;
import cn.test.demo.enums.OrderStatusEnum;
import cn.test.demo.enums.PayStatusEnum;
import cn.test.demo.utils.EnumUtil;
import cn.test.demo.utils.serializer.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL) // 如果数据为null，则不反回前段
public class OrderDTO {
    // 订单 id
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
    private  Integer orderStatus;

    // 支付状态，默认未支付 0
    private Integer payStatus;

    // 创建时间
   // @CreatedDate
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    // 更新时间
    //@LastModifiedDate
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
    List<OrderDetail> orderDetailList;
    //List<OrderDetail> orderDetailList =new ArrayList<>(); // 初始值，在返回给前端显示 【】

    @JsonIgnore
    public  OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getBycode(orderStatus,OrderStatusEnum.class);
    }

    // 通过code 获取enum 的对象,  在Json 序列化的时候，忽略这两个方法
    @JsonIgnore
    public  PayStatusEnum getPayStatusEnum(){
        return  EnumUtil.getBycode(payStatus,PayStatusEnum.class);
    }
}
