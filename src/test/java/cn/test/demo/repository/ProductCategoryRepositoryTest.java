package cn.test.demo.repository;

import cn.test.demo.dataobject.ProductCategory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*
 * @author: Max Yang
 * @date: 2021-01-29 14:29
 * @desc:
 */
//


//        ╔═════════════════════╦═══════════════════════╗
//        ║      Old name       ║       New name        ║
//        ╠═════════════════════╬═══════════════════════╣
//        ║ findOne(…)          ║ findById(…)           ║
//        ╠═════════════════════╬═══════════════════════╣
//        ║ save(Iterable)      ║ saveAll(Iterable)     ║
//        ╠═════════════════════╬═══════════════════════╣
//        ║ findAll(Iterable)   ║ findAllById(…)        ║
//        ╠═════════════════════╬═══════════════════════╣
//        ║ delete(ID)          ║ deleteById(ID)        ║
//        ╠═════════════════════╬═══════════════════════╣
//        ║ delete(Iterable)    ║ deleteAll(Iterable)   ║
//        ╠═════════════════════╬═══════════════════════╣
//        ║ exists()            ║ existsById(…)         ║
//        ╚═════════════════════╩═══════════════════════╝

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public  void  findOneTest(){
        Optional<ProductCategory> productCategory = repository.findById(1);
        System.out.println( productCategory.toString());
    }

    @Test
    @Transactional  // 写完数据后 立即 回滚数据，数据并不保存
    public  void  saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱2");
        productCategory.setCategoryType(41);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotEquals(null,result);
    }

    @Test
    public  void  updateTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }


    @Test
    public  void  findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategory> res = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,res.size());
    }
}