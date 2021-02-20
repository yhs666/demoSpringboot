package cn.test.demo.servie.impl; /*
 * @author: Max Yang
 * @date: 2021-01-30 7:04
 * @desc:
 */

import cn.test.demo.dataobject.ProductInfo;
import cn.test.demo.dto.CartDTO;
import cn.test.demo.enums.ProductStatus;
import cn.test.demo.enums.ResoultEnum;
import cn.test.demo.exception.SellException;
import cn.test.demo.repository.ProductInfoRepository;
import cn.test.demo.servie.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatus.Up.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }


    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOSList) {
        for (CartDTO cartDTO:cartDTOSList){
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);
            if (productInfo == null){
                throw  new SellException(ResoultEnum.PRODUCT_NOT_EXIST);
            }

            Integer res = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(res);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void descreaseStock(List<CartDTO> cartDTOSList) {
        for (CartDTO cartDto: cartDTOSList){
            ProductInfo productInfo = repository.findById(cartDto.getProductId()).orElse(null);
            if (productInfo == null){
                throw  new SellException(ResoultEnum.PRODUCT_NOT_EXIST);
            }

            Integer res = productInfo.getProductStock() - cartDto.getProductQuantity();
            if(res <0){
                throw  new SellException(ResoultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(res);
            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).orElse(null);
        if (productInfo == null){
            throw  new SellException(ResoultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatus.Up){
            throw  new SellException(ResoultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatus.Up.getCode());

        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).orElse(null);
        if (productInfo == null){
            throw  new SellException(ResoultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatus.DOWN){
            throw  new SellException(ResoultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatus.Up.getCode());
        return repository.save(productInfo);
    }
}
