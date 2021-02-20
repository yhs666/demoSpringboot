package cn.test.demo.controller;

/*
 * @author: Max Yang
 * @date: 2021-02-15 5:21
 * @desc:  卖家端订单管理
 */

import cn.test.demo.dto.OrderDTO;
import cn.test.demo.enums.ResoultEnum;
import cn.test.demo.exception.SellException;
import cn.test.demo.servie.OrderService;
import cn.test.demo.utils.ResoultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page  第几页
     * @param size  每页多少数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue="1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest= PageRequest.of(page-1,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return  new ModelAndView("order/list",map);
    }

    /**
     * 取消订单操作
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancle(@RequestParam("orderId") String orderId,
                             Map<String,Object> map) {
        OrderDTO orderDTO;
        try{
            orderDTO= orderService.findOne(orderId);
            orderService.cancle(orderDTO);
        }catch (SellException e){
            log.error("[卖家端取消订单]， 订单查询不到");
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return  new ModelAndView("common/error",map);
        }

        map.put("msg", ResoultEnum.ORDER_CANCEL_SUCCESS);
        map.put("url","/sell/seller/order/list");

        return  new ModelAndView("common/success",map);


    }
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String,Object> map) {
        OrderDTO orderDTO;
        try{
            orderDTO= orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("[卖家端完结错误]， 订单查询不到");
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return  new ModelAndView("common/error",map);
        }

        map.put("msg", ResoultEnum.ORDER_FINISH_SUCCESS);
        map.put("url","/sell/seller/order/list");

        return  new ModelAndView("common/success",map);


    }
}
