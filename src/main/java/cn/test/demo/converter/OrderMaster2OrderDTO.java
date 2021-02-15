package cn.test.demo.converter; /*
 * @author: Max Yang
 * @date: 2021-02-01 2:33
 * @desc:
 */

import cn.test.demo.dataobject.OrderMaster;
import cn.test.demo.dto.OrderDTO;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDTO {
    public  static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return  orderDTO;
    }

    public  static List<OrderDTO>  convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e->convert(e)
                ).collect(Collectors.toList());

    }
}
