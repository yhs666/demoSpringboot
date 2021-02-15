package cn.test.demo.servie.impl; /*
 * @author: Max Yang
 * @date: 2021-01-30 5:26
 * @desc:
 */

import cn.test.demo.dataobject.ProductCategory;
import cn.test.demo.repository.ProductCategoryRepository;
import cn.test.demo.servie.CategoryService;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
* Service 层实现
* */

//@Proxy(lazy = false)
@Service
public class CategoryServiceImpl  implements CategoryService {
    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
