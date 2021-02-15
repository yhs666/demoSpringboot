package cn.test.demo.repository; /*
 * @author: Max Yang
 * @date: 2021-01-31 6:12
 * @desc:
 */

import cn.test.demo.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId(String orderId);
}
