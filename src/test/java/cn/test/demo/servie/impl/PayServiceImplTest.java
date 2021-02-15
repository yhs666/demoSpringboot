package cn.test.demo.servie.impl;

import cn.test.demo.dto.OrderDTO;
import cn.test.demo.servie.OrderService;
import cn.test.demo.servie.PayService;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @author: Max Yang
 * @date: 2021-02-03 13:46
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;

    @Test
    public void create() {

        OrderDTO orderDTO = orderService.findOne("16121594572738094672");
        payService.create(orderDTO);
    }

    @Test
    public  void  refund(){
        OrderDTO orderDTO = orderService.findOne("16132769013842996565");
        payService.refund(orderDTO);
    }
}