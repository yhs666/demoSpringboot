package cn.test.demo.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
* 类目
 * @author: Max Yang
 * @date: 2021-01-29 14:14
 * @desc:
 */
@Entity
//@Proxy(lazy = false)
@DynamicUpdate //动态更新-需要设置数据库的更新时间字段为自动更新 这样，查询出时间，去设置其他字段后保存，更新时间依然会更新
@Data //不用写setter和getter方法,toString也可以省了 性能是一样的，可以去看编译的class文件，和我们写的一样
public class ProductCategory {
    // 类目 id
    @Id
    //@GeneratedValue  单独这样写，后续写数据报错
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer categoryId;
    // 类目名字
    private  String categoryName;
    // 类目编号
    private  Integer categoryType;

}
