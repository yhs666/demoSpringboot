package cn.test.demo.repository;

import cn.test.demo.dataobject.OrderDetail;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @author: Max Yang
 * @date: 2021-01-31 7:20
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private  OrderDetailRepository repository;

    @Test
    public   void saveTest() throws  Exception{
        OrderDetail orderDetail= new OrderDetail();
        orderDetail.setDetailId("123456789");
        orderDetail.setOrderId("11111111");
        orderDetail.setProductIcon("http://test.png");
        orderDetail.setProductName("宫保鸡丁");
        orderDetail.setProductPrice(new BigDecimal(13.5));
        orderDetail.setProductQuantity(3);
        orderDetail.setProductId("123");
        OrderDetail orderDetail1=repository.save(orderDetail);
        Assert.assertNotNull(orderDetail);
    }

    @Test
    public void findByOrderId() throws  Exception{
        List<OrderDetail> orderDetailList = repository.findByOrderId("11111111");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}