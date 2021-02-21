package cn.test.demo.repository; /*
 * @author: Max Yang
 * @date: 2021-02-21 7:39
 * @desc:
 */

import cn.test.demo.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);
}
