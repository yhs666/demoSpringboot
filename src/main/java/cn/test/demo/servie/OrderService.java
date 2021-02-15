package cn.test.demo.servie; /*
 * @author: Max Yang
 * @date: 2021-01-31 8:19
 * @desc:
 */

import cn.test.demo.dataobject.OrderMaster;
import cn.test.demo.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface OrderService {
    // 创建订单
    OrderDTO create(OrderDTO orderDTO);
    // 查询单个订单
    OrderDTO findOne(String orderId);
    // 查询订单列表
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);
    // 取消订单
    OrderDTO cancle(OrderDTO orderDTO);
    // 完结订单
    OrderDTO finish(OrderDTO orderDTO);
    // 支付订单
    OrderDTO paid(OrderDTO orderDTO);

    // 查询订单列表
    Page<OrderDTO> findList( Pageable pageable);
}
