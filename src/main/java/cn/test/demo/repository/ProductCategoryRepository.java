package cn.test.demo.repository;

/*
 * @author: Max Yang
 * @date: 2021-01-29 14:26
 * @desc:
 */

import cn.test.demo.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory>  findByCategoryTypeIn(List<Integer> categoryTypeList);
}
