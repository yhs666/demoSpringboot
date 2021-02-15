package cn.test.demo.servie.impl;



import cn.test.demo.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @author: Max Yang
 * @date: 2021-01-30 9:56
 * @desc:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private  ProductServiceImpl productService;
    @Test
    public void findOne() throws  Exception {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertEquals("123456",productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception{
        List<ProductInfo> productInfoList= productService.findUpAll();

        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() throws  Exception{
        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> productInfoList= productService.findAll(request);
        System.out.println(productInfoList);
        Assert.assertNotEquals(0,productInfoList.getTotalElements());
    }


    @Test
    public  void save() throws  Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(2);
        productInfo.setProductStock(100);
        productInfo.setProductStatus(0);
        productInfo.setProductDescription("very good");
        productInfo.setProductIcon("http:\\test.com\test.png");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductId("1234567");
        ProductInfo productInfo1 = productService.save(productInfo);

        Assert.assertNotEquals(null,productInfo1);
    }
}