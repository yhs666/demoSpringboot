package cn.test.demo.servie; /*
 * @author: Max Yang
 * @date: 2021-01-30 7:00
 * @desc:
 */

import cn.test.demo.dataobject.ProductInfo;
import cn.test.demo.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// 商品
public interface ProductService {

    ProductInfo findOne(String productId);

    List<ProductInfo>  findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void  increaseStock(List<CartDTO> cartDTOSList);

    //减 库存
    void  descreaseStock(List<CartDTO> cartDTOSList);
}
