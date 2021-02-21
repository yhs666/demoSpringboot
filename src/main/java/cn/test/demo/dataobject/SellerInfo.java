package cn.test.demo.dataobject; /*
 * @author: Max Yang
 * @date: 2021-02-21 7:35
 * @desc:
 */

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class SellerInfo {
    @Id
    private  String sellerId;

    private  String username;
    private  String password;
    private  String openid;
    // 创建时间
    @CreatedDate
    private Date createTime;
    // change 时间
    @LastModifiedDate
    private  Date updateTime;
}
