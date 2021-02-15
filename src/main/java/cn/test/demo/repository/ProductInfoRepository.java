package cn.test.demo.repository;

/*
 * @author: Max Yang
 * @date: 2021-01-30 6:36
 * @desc:
 */

import cn.test.demo.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    //查询上架的商品
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
