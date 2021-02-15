package cn.test.demo.servie.impl; /*
 * @author: Max Yang
 * @date: 2021-01-31 9:04
 * @desc:
 */

import cn.test.demo.converter.OrderMaster2OrderDTO;
import cn.test.demo.dataobject.OrderDetail;
import cn.test.demo.dataobject.OrderMaster;
import cn.test.demo.dataobject.ProductInfo;
import cn.test.demo.dto.CartDTO;
import cn.test.demo.dto.OrderDTO;
import cn.test.demo.enums.OrderStatusEnum;
import cn.test.demo.enums.PayStatusEnum;
import cn.test.demo.enums.ResoultEnum;
import cn.test.demo.exception.SellException;
import cn.test.demo.repository.OrderDetailRepository;
import cn.test.demo.repository.OrderMasterRepository;
import cn.test.demo.servie.OrderService;
import cn.test.demo.servie.PayService;
import cn.test.demo.servie.ProductService;
import cn.test.demo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private PayService payService;
    @Override
    @Transactional  //数据库事务处理，如果以下函数发生错误异常，写入的数据回滚，不会写入数据库
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        // 1 查询商品的数量 和价格
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo= productService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResoultEnum.PRODUCT_NOT_EXIST);
            }
            // 2 计算总价
            orderAmount = productInfo
                    .getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            // 分别写入订单数据库 orderMaster 和 orderDetail
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        // 分别写入订单数据库 orderMaster 和 orderDetail
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        //orderMaster.setOrderId(orderId);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(orderAmount);


        orderMasterRepository.save(orderMaster);
        // 4  扣库存
        List<CartDTO> cartDTOList = orderDTO
                                    .getOrderDetailList()
                                    .stream()
                                    .map(e-> new CartDTO(e.getProductId(),e.getProductQuantity()))
                                    .collect(Collectors.toList());
        productService.descreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster=orderMasterRepository.findById(orderId).orElse(null);
        if (orderMaster == null){
            throw  new SellException(ResoultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetails= orderDetailRepository.findByOrderId(orderId);
        OrderDTO  orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    /*
    * 根据客户的openid 返回订单数量
    * */
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasters = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.convert(orderMasters.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasters.getTotalElements());

        return orderDTOPage;
    }

    @Override
    /*
    * 客户 取消商品 订单的相关 操作
    * */
    @Transactional
    public OrderDTO cancle(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        // 判断订单状态
        if(! orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("取消订单  订单状态不正确 orderId={}， orderStatus={} ",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResoultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster1 =orderMasterRepository.save(orderMaster);

        if (orderMaster1==null){
            log.error(" [取消订单] 更新失败, orderMaster={}",orderMaster);
            throw new SellException(ResoultEnum.ORDER_UPDATE_FAIL);
        }
        // 返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error(" [取消订单] 订单中无商品, orderMaster={}",orderMaster);
            throw new SellException(ResoultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e-> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //如果已经支付，需要退款

        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订状态
        if (! orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error(" [订单完结] 订单状态不正确 ，orderId={}，orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResoultEnum.ORDER_STATUS_ERROR);
        }
        //
        // 修改状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster orderMaster1 =orderMasterRepository.save(orderMaster);
        if (orderMaster1 ==null){
            log.error(" [订单完结] 保存数据异常 ，orderMaster={}",orderDTO);
            throw new SellException(ResoultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订状态
        if (! orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error(" [订单支付] 订单状态不正确 ，orderId={}，orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResoultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (! orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error(" [订单支付] 订单支付不正确 ，orderId={}，getPayStatus={}",orderDTO.getOrderId(),orderDTO.getPayStatus());
            throw new SellException(ResoultEnum.ORDER_PAY_STATUS_ERROR);
        }
        // 修改支付状态

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster1 =orderMasterRepository.save(orderMaster);
        if (orderMaster1 ==null){
            log.error(" [订单支付] 保存数据异常 ，orderMaster={}",orderDTO);
            throw new SellException(ResoultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
