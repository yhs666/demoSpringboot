package cn.test.demo.servie.impl;



import cn.test.demo.dataobject.SellerInfo;
import cn.test.demo.servie.SellerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @author: Max Yang
 * @date: 2021-02-21 8:03
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public  class SellerServiceImplTest {

    @Autowired
    private SellerServiceImpl sellerService;

    private  static  String openid="abc";
    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        Assert.assertNotNull(sellerInfo);
    }
}