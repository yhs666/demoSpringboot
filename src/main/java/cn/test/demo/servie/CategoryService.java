package cn.test.demo.servie;

/*
 * @author: Max Yang
 * @date: 2021-01-30 5:21
 * @desc:
 */

import cn.test.demo.dataobject.ProductCategory;

import java.util.List;
import java.util.Optional;
/*
* service 层接口
* */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
