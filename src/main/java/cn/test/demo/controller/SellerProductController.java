package cn.test.demo.controller;

/*
 * @author: Max Yang
 * @date: 2021-02-20 3:02
 * @desc:
 */

import cn.test.demo.dataobject.ProductCategory;
import cn.test.demo.dataobject.ProductInfo;
import cn.test.demo.dto.OrderDTO;
import cn.test.demo.exception.SellException;
import cn.test.demo.from.ProductFrom;
import cn.test.demo.servie.CategoryService;
import cn.test.demo.servie.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    /**
     * 商品列表
     * @param page  第几页
     * @param size  每页多少数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue="1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest= PageRequest.of(page-1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return  new ModelAndView("product/list",map);
    }
    //商品上架
    @GetMapping("/on_sale")
    public  ModelAndView onSale(@RequestParam("productId") String productId,
                                Map<String,Object> map){
        try {
            ProductInfo productInfo =productService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return  new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return  new ModelAndView("common/success",map);
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public  ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String,Object> map){
        try {
            ProductInfo productInfo =productService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return  new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return  new ModelAndView("common/success",map);
    }


    @GetMapping("/index")
    public  ModelAndView index(@RequestParam(value="productId",required = false) String productId,
                                 Map<String,Object> map){
        if(StringUtils.hasLength(productId)){
            ProductInfo  productInfo=productService.findOne(productId);
            map.put("productInfo",productInfo);

        }
        // search 类目
        List<ProductCategory> categoryList= categoryService.findAll();

        map.put("categoryList",categoryList);
        return  new ModelAndView("product/index",map);
    }

    /**
     * 保存更新 都使用该方法
     * @param from
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public  ModelAndView save(@Valid ProductFrom from,
                              BindingResult bindingResult,
                              Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return  new ModelAndView("common/error",map);
        }



        try{
            ProductInfo productInfo = productService.findOne(from.getProductId());
            BeanUtils.copyProperties(from,productInfo);
            productService.save(productInfo);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return  new ModelAndView("common/error",map);
        }

        map.put("url","/sell/seller/product/list");
        return  new ModelAndView("common/success",map);
    }
}
