package cn.test.demo.servie.impl; /*
 * @author: Max Yang
 * @date: 2021-02-01 9:23
 * @desc:
 */

import cn.test.demo.dto.OrderDTO;
import cn.test.demo.enums.ResoultEnum;
import cn.test.demo.exception.SellException;
import cn.test.demo.servie.BuyerService;
import cn.test.demo.servie.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = CheckOrderOwner(openid,orderId);
        if (orderDTO == null) {
            log.error(" [取消订单] 订单不存在， orderId={}",orderId);
            throw new SellException(ResoultEnum.ORDER_NOT_EXIST);
        }

        return  orderService.cancle(orderDTO);
    }

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return  CheckOrderOwner(openid,orderId);
    }

    private OrderDTO CheckOrderOwner(String openid, String orderId) {

        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO ==null) return  null;
        if (! orderDTO.getBuyerOpenid().equals(openid)){
            log.error(" [查询订单] 订单的openid 不一致， openid={}， orderDto={}",openid,orderDTO);
            throw new SellException(ResoultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
