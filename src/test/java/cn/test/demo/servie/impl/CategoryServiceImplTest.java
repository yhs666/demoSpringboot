package cn.test.demo.servie.impl;

import cn.test.demo.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @author: Max Yang
 * @date: 2021-01-30 5:42
 * @desc:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private  CategoryServiceImpl categoryService;

    @Test
    public void findOne() throws  Exception {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception{
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() throws  Exception {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3));
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void save() throws  Exception {
        ProductCategory productCategory= new ProductCategory();
        productCategory.setCategoryName("男生欢迎");
        productCategory.setCategoryType(5);
        ProductCategory productCategory1 =categoryService.save(productCategory);
        Assert.assertNotEquals(null,productCategory);
    }
}