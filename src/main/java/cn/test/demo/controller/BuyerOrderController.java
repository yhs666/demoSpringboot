package cn.test.demo.controller; /*
 * @author: Max Yang
 * @date: 2021-02-01 4:58
 * @desc:
 */

import cn.test.demo.ViewObject.ResoultObject;
import cn.test.demo.converter.OrderForm2OrderDTO;
import cn.test.demo.dto.OrderDTO;
import cn.test.demo.enums.ResoultEnum;
import cn.test.demo.exception.SellException;
import cn.test.demo.from.OrderForm;
import cn.test.demo.servie.BuyerService;
import cn.test.demo.servie.OrderService;
import cn.test.demo.utils.ResoultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/buyer/order")
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;
    // 创建订单
    @PostMapping("/create")
    public ResoultObject<Map<String,String>> create(@Valid OrderForm orderForm,

                                                    BindingResult bindingResult){
        //为测试支付，写死openid
        if (orderForm.getOpenid().isEmpty()){
            orderForm.setOpenid("oTgZpwdJgtFhCNdnBWLJl_joeums");
        }
//        if (bindingResult.hasErrors()){
//            log.error("[创建订单] 参数不正确， orderFrom={}",orderForm);
//            throw  new SellException(ResoultEnum.PARAM_ERROR.getCode(),
//                    bindingResult.getFieldError().getDefaultMessage());
//        }
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        //判断购物车 是否为空

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[创建订单] 购物车不能为空， orderFrom={}",orderForm);
            throw  new SellException(ResoultEnum.CART_EMPTY);
        }
        OrderDTO orderDTO1 = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderDTO1.getOrderId());
        return ResoultVOUtil.success(map);
    }
    // 订单列表
    @GetMapping("/list")
    public  ResoultObject<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                               @RequestParam(value = "page" ,defaultValue = "0") Integer page,
                                               @RequestParam(value = "size",defaultValue = "10") Integer size){
        //为测试支付，写死openid
        if (openid.isEmpty()){
            openid="oTgZpwdJgtFhCNdnBWLJl_joeums";
        }
        if(! StringUtils.hasLength(openid)){
            log.error("[查询订单列表] openid 为空");
            throw new SellException(ResoultEnum.PARAM_ERROR);
        }
        PageRequest request = PageRequest.of(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);

        return  ResoultVOUtil.success(orderDTOPage.getContent());


    }
    // 订单详情
    @GetMapping("/detail")
    public  ResoultObject<OrderDTO> detail(@RequestParam("openid") String openid,
                                           @RequestParam("orderId") String orderId){
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);

        return  ResoultVOUtil.success(orderDTO);
    }
    //
    // 取消订单
    @PostMapping("/cancle")
    public  ResoultObject<OrderDTO> cancle(@RequestParam("openid") String openid,
                                           @RequestParam("orderId") String orderId){

         buyerService.cancelOrder(openid,orderId);

        return  ResoultVOUtil.success();
    }
    //
    //

}
