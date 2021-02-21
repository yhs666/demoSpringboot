package cn.test.demo.from; /*
 * @author: Max Yang
 * @date: 2021-02-21 7:01
 * @desc:
 */

import lombok.Data;

@Data
public class ProductCategoryFrom {

    private  Integer categoryId;
    // 类目名字
    private  String categoryName;
    // 类目编号
    private  Integer categoryType;
}
