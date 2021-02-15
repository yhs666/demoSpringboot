package cn.test.demo.controller; /*
 * @author: Max Yang
 * @date: 2021-02-03 13:06
 * @desc:
 */

import cn.test.demo.dto.OrderDTO;
import cn.test.demo.enums.ResoultEnum;
import cn.test.demo.exception.SellException;
import cn.test.demo.servie.OrderService;
import cn.test.demo.servie.PayService;
import cn.test.demo.servie.impl.PayServiceImpl;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayServiceImpl payService;

    /*
    *  由于借用的秘钥，需要用借用的url 访问
    *  http://proxy.springboot.cn/pay?openid=oTgZpwdJgtFhCNdnBWLJl_joeums&orderId=16121594572738094672&returnUrl=http://www.imooc.com
    * */
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map
                         ){
        log.info("Pay ReturnUrl:{}",returnUrl);
        //http://sell.9test.cn/#/order/16126755147933808757
        //由于使用的是借用的支付账号，需要更改到借用的url上才行
        //returnUrl = returnUrl.replace("sell.9test.cn","proxy.springboot.cn");
        //查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO== null){
            throw   new SellException(ResoultEnum.ORDER_NOT_EXIST);
        }
        //发起支付
       PayResponse payResponse= payService.create(orderDTO);
        map.put("payResponse",payResponse);
        map.put("returnUrl", returnUrl);
        return  new ModelAndView("pay/create",map);


    }
    /*
    *  在用户支付完成，微信支付服务器异步 回调通知，通知本地服务器用户支付完成
    * */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData ){
        //支付回调
        PayResponse payResponse= payService.notify(notifyData);

        // 返回给微信 处理结果
        return new ModelAndView("pay/success");

    }
    @GetMapping("/test")
    public ModelAndView test(Map<String,Object> map){
        map.put("msg","This Message from paycontroller");
        log.info("[Test function]");
        return  new ModelAndView("pay/test",map);
    }
}
