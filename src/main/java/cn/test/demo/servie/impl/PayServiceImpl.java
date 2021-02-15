package cn.test.demo.servie.impl; /*
 * @author: Max Yang
 * @date: 2021-02-03 13:13
 * @desc:
 */

import cn.test.demo.dto.OrderDTO;
import cn.test.demo.enums.ResoultEnum;
import cn.test.demo.exception.SellException;
import cn.test.demo.servie.OrderService;
import cn.test.demo.servie.PayService;
import cn.test.demo.utils.JsonUtil;
import cn.test.demo.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;

import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Resource
    private  BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;

    private  static final String ORDER_NAME="微信点单订餐";
    @Override
    public PayResponse create(OrderDTO orderDTO) {

        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info(" [微信支付] 发起支付 Request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse =bestPayService.pay(payRequest);

        log.info(" [微信支付] 发起支付 payResponse={}",JsonUtil.toJson(payResponse));

        return  payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
       PayResponse payResponse = bestPayService.asyncNotify(notifyData);
       log.info("[微信支付] 异步通知 payResponse={}",JsonUtil.toJson(payResponse));
       // search order id
       OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
       // check order id exists
        if(orderDTO==null){
            log.error(" [微信支付] 异步通知，订单不存在，orderId={}",payResponse.getOrderId());
            throw  new SellException(ResoultEnum.ORDER_NOT_EXIST);
        }
       // 验证金额  sdk done
        // 支付状态  sdk done

        //支付金额(0.10 != 0.1)
        if(! MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())){
            log.error(" [微信支付] 异步通知，金额不匹配，orderId={}，微信通知金额={}，系统金额={}",
                    payResponse.getOrderId(),payResponse.getOrderAmount(),orderDTO.getOrderAmount());
            throw new SellException(ResoultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        // 支付人 下单人==支付人
       //change order pay status
        orderService.paid(orderDTO);
       return  payResponse;
    }

    /**
     * 微信退款
     * @param orderDTO
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        // wechat H5 pay
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信退款] request={}",refundRequest);

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[微信退款] Response={}",refundResponse);
        return  refundResponse;
    }
}
