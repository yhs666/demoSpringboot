package cn.test.demo.repository;



import cn.test.demo.dataobject.SellerInfo;
import cn.test.demo.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @author: Max Yang
 * @date: 2021-02-21 7:43
 * @desc:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public  class SellerInfoRepositoryTest {

    @Autowired
    private  SellerInfoRepository repository;
    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");
        SellerInfo sellerInfo2 = repository.save(sellerInfo);
        Assert.assertNotNull(sellerInfo2);
    }
    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = repository.findByOpenid("abc");

        Assert.assertEquals(sellerInfo.getOpenid(),"abc");
    }

}