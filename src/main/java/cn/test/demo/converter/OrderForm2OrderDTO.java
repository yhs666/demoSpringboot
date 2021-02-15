package cn.test.demo.converter; /*
 * @author: Max Yang
 * @date: 2021-02-01 5:25
 * @desc:
 */

import cn.test.demo.dataobject.OrderDetail;
import cn.test.demo.dto.OrderDTO;
import cn.test.demo.enums.ResoultEnum;
import cn.test.demo.exception.SellException;
import cn.test.demo.from.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDTO {

    public   static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        // Json 格式转换
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
             orderDetails = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error(" [对象转换] 错误，string ={}",orderForm.getItems());
            throw   new SellException(ResoultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetails);
        return  orderDTO;

    }
}
