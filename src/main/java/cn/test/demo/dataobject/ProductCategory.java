package cn.test.demo.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/*
* 类目
 * @author: Max Yang
 * @date: 2021-01-29 14:14
 * @desc:
 */
@Entity
//@Proxy(lazy = false)
@DynamicUpdate //动态更新-需要设置数据库的更新时间字段为自动更新 这样，查询出时间，去设置其他字段后保存，更新时间依然会更新
@Data //不用写setter和getter方法,toString也可以省了 性能是一样的，可以去看编译的class文件，和我们写的一样\
@EntityListeners(AuditingEntityListener.class)
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
    // 创建时间
    @CreatedDate
    private Date createTime;
    // change 时间
    @LastModifiedDate
    private  Date updateTime;

//    public ProductCategory(String categoryName, Integer categoryType) {
//        this.categoryName = categoryName;
//        this.categoryType = categoryType;
//    }
}
