package cn.test.demo.controller; /*
 * @author: Max Yang
 * @date: 2021-02-21 6:45
 * @desc:
 */

import cn.test.demo.dataobject.ProductCategory;
import cn.test.demo.dataobject.ProductInfo;
import cn.test.demo.exception.SellException;
import cn.test.demo.from.ProductCategoryFrom;
import cn.test.demo.from.ProductFrom;
import cn.test.demo.servie.CategoryService;
import cn.test.demo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列出所有类型
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list( Map<String,Object> map){
        List<ProductCategory> categoryList =categoryService.findAll();

        map.put("categoryList",categoryList);
        return  new ModelAndView("category/list",map);
    }

    /**
     *  对相应的分类进行修改
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public  ModelAndView index(@RequestParam(value="categoryId",required = false) Integer categoryId,
                               Map<String,Object> map){
        if(categoryId != null){
            ProductCategory  productCategory=categoryService.findOne(categoryId);
            map.put("category",productCategory);
        }
        return  new ModelAndView("category/index",map);
    }

    /**
     * 保存更新 都使用该方法
     * @param from
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public  ModelAndView save(@Valid ProductCategoryFrom from,
                              BindingResult bindingResult,
                              Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return  new ModelAndView("common/error",map);
        }

        ProductCategory productCategory = new ProductCategory();
        try{
            if(from.getCategoryId() != null){
                productCategory = categoryService.findOne(from.getCategoryId());
            }

            BeanUtils.copyProperties(from,productCategory);
            categoryService.save(productCategory);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/index");
            return  new ModelAndView("common/error",map);
        }

        map.put("url","/sell/seller/category/list");
        return  new ModelAndView("common/success",map);
    }
}
