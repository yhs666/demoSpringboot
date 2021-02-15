package cn.test.demo.servie; /*
 * @author: Max Yang
 * @date: 2021-02-01 9:20
 * @desc:
 */

import cn.test.demo.dto.OrderDTO;

public interface BuyerService {

    // 取消订单

    OrderDTO cancelOrder(String  openid,String orderId);

    // 查询订单

    OrderDTO findOrderOne(String  openid,String orderId);
}
