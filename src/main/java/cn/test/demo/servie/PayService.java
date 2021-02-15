package cn.test.demo.servie; /*
 * @author: Max Yang
 * @date: 2021-02-03 13:12
 * @desc:
 */


import cn.test.demo.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

public interface PayService {

    PayResponse create(OrderDTO orderDTO);
    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
