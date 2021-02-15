package cn.test.demo.controller; /*
 * @author: Max Yang
 * @date: 2021-01-30 10:09
 * @desc:
 */

import cn.test.demo.ViewObject.ProductInfoViewObject;
import cn.test.demo.ViewObject.ProductViewObject;
import cn.test.demo.ViewObject.ResoultObject;
import cn.test.demo.dataobject.ProductCategory;
import cn.test.demo.dataobject.ProductInfo;
import cn.test.demo.servie.CategoryService;
import cn.test.demo.servie.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static cn.test.demo.utils.ResoultVOUtil.success;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResoultObject<ProductInfo> list(){
        //1 查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2 查询所有类目
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e-> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //数据的拼装
        List<ProductViewObject> productViewObjectList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList){
            ProductViewObject productViewObject = new ProductViewObject();
            productViewObject.setCategoryName(productCategory.getCategoryName());
            productViewObject.setCategoryType(productCategory.getCategoryType());

            // 商品详情
            List<ProductInfoViewObject> productInfoViewObjectList = new ArrayList<>();

            for (ProductInfo productInfo:productInfoList){
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())) {
                    ProductInfoViewObject productInfoViewObject = new ProductInfoViewObject();
//                    productInfoViewObject.setProductDescription(productInfo.getProductDescription());
//                    productInfoViewObject.setProductIcon(productInfo.getProductIcon());
//                    productInfoViewObject.setProductId(productInfo.getProductId());
//                    productInfoViewObject.setProductPrice(productInfo.getProductPrice());
//                    productInfoViewObject.setProductName(productInfo.getProductName());
                    BeanUtils.copyProperties(productInfo, productInfoViewObject);
                    productInfoViewObjectList.add(productInfoViewObject);
                }
            }
            productViewObject.setProductInfoViewObjectList(productInfoViewObjectList);
            productViewObjectList.add(productViewObject);
        }



        return  success(productViewObjectList);

    }

}
