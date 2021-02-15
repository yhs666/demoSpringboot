package cn.test.demo.servie.impl;



import cn.test.demo.dataobject.OrderDetail;
import cn.test.demo.dataobject.OrderMaster;
import cn.test.demo.dto.OrderDTO;
import cn.test.demo.repository.OrderDetailRepository;
import cn.test.demo.repository.OrderMasterRepository;
import cn.test.demo.servie.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @author: Max Yang
 * @date: 2021-01-31 12:43
 * @desc:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public  class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    private  final  String buyerOpenid="110110";
    private  final  String ORDERID= "16120989998512866366";
    @Test
    public  void create() {
        OrderDTO orderDTO = new OrderDTO();
        //orderDTO.setOrderId("123456");
        orderDTO.setBuyerName("Max");
        orderDTO.setBuyerAddress("shang di donglu");
        orderDTO.setBuyerPhone("12345678901");
        orderDTO.setOrderAmount(new BigDecimal(3.4));
        orderDTO.setBuyerOpenid(buyerOpenid);

        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductQuantity(3);
        orderDetail.setProductId("123456");
        orderDetails.add(orderDetail);

        orderDetail = new OrderDetail();
        orderDetail.setProductQuantity(1);
        orderDetail.setProductId("1234567");
        orderDetails.add(orderDetail);

        orderDTO.setOrderDetailList(orderDetails);

        OrderDTO orderDTO1 = orderService.create(orderDTO);
        log.info(" [ 创建订单 ] result={}",orderDTO1);
        Assert.assertNull(orderDTO1);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        log.info("查询订单 结果： {}" ,orderDTO);
        Assert.assertEquals(ORDERID,orderDTO.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest = PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(buyerOpenid,pageRequest);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public  void cancle() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO orderDTO1 = orderService.cancle(orderDTO);
        Assert.assertEquals(orderDTO,orderDTO1);
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO orderDTO1 = orderService.finish(orderDTO);
        Assert.assertEquals(orderDTO,orderDTO1);
    }

    @Test
    public  void paid() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO orderDTO1 = orderService.paid(orderDTO);
        Assert.assertEquals(orderDTO,orderDTO1);
    }
}