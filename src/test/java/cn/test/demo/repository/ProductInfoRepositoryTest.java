package cn.test.demo.repository;


import cn.test.demo.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @author: Max Yang
 * @date: 2021-01-30 6:39
 * @desc:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository repository;


    @Test
    public void saveTest() {

        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(2);
        productInfo.setProductStock(100);
        productInfo.setProductStatus(0);
        productInfo.setProductDescription("very good");
        productInfo.setProductIcon("http:\\test.com\test.png");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductId("123456");

        ProductInfo resoult = repository.save(productInfo);
        Assert.assertNotNull(resoult);
    }

    @Test
    public void  findByProductStatus() throws Exception{
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }
}