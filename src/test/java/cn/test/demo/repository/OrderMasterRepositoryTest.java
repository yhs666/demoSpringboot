package cn.test.demo.repository;

import cn.test.demo.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityListeners;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @author: Max Yang
 * @date: 2021-01-31 6:14
 * @desc:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest {
    @Autowired
    private  OrderMasterRepository  repository;
    private  final  String OPENID="110110";
    @Test
    public void  saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("Max");
        orderMaster.setBuyerAddress("shang di donglu");
        orderMaster.setBuyerPhone("12345678901");
        orderMaster.setOrderAmount(new BigDecimal(3.4));
        orderMaster.setBuyerOpenid("110110");
        OrderMaster orderMaster1 =repository.save(orderMaster);
        Assert.assertNotNull(orderMaster1);
    }
    @Test
    public void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(0,1);
        Page<OrderMaster>   orderMasters= repository.findByBuyerOpenid(OPENID,request);

        Assert.assertNotEquals(0,orderMasters.getTotalElements());
    }
}