package com.hodor.demo.service.impl;

import com.hodor.demo.Dao.OrderDetailDao;
import com.hodor.demo.Dao.OrderMasterDao;
import com.hodor.demo.converter.OrderMaster2OrderDTO;
import com.hodor.demo.dataobject.OrderDetail;
import com.hodor.demo.dataobject.OrderMaster;
import com.hodor.demo.dataobject.ProductInfo;
import com.hodor.demo.dto.CartDTO;
import com.hodor.demo.dto.OrderDTO;
import com.hodor.demo.enums.OrderStatus;
import com.hodor.demo.enums.PayStatus;
import com.hodor.demo.enums.ResultEnum;
import com.hodor.demo.exception.SellException;
import com.hodor.demo.service.OrderService;
import com.hodor.demo.service.ProductService;
import com.hodor.demo.utils.KeyUtil;
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

/**
 * Created By Fan Huiliang
 * 2019-07-08 20:47
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMasterDao orderMasterDao;

    @Autowired
    OrderDetailDao orderDetailDao;

    @Autowired
    ProductService productService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //查询商品
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.product_not_exit);
            }
            //计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailDao.save(orderDetail);

        }

        //写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster); //注意先拷贝 防止覆盖下面两条数据

        orderMaster.setOrderAmount(orderAmount);

        orderMasterDao.save(orderMaster);

        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream().map(e->
                new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster orderMaster = orderMasterDao.findById(orderId).get();
        if(orderMaster == null){
            throw new SellException(ResultEnum.order_not_exit);
        }

        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.order_detail_not_exit);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.
                findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.
                convert(orderMasterPage.getContent());

        return new PageImpl<OrderDTO>(
                orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())){
            throw new SellException(ResultEnum.order_status_error);
        }
        //转换订单状态
        orderDTO.setOrderStatus(OrderStatus.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if(updateResult == null){
            throw new SellException(ResultEnum.order_update_fail);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            throw new SellException(ResultEnum.order_detail_empty);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e-> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //如果已支付需要退款
        if(orderDTO.getPayStatus().equals(PayStatus.SUCCESS.getCode())){
            //payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断状态
        if(!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())){
            throw new SellException(ResultEnum.order_status_error);
        }
        //修改状态
        orderDTO.setOrderStatus(OrderStatus.FINISH.getCode());
        OrderMaster ordermaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,ordermaster);
        OrderMaster updateResult = orderMasterDao.save(ordermaster);
        if(updateResult == null){
            throw new SellException(ResultEnum.order_update_fail);
        }
       return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())){
            throw new SellException(ResultEnum.order_status_error);
        }
        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatus.WAIT.getCode())){
            throw new SellException(ResultEnum.order_pay_status_error);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatus.SUCCESS.getCode());
        OrderMaster ordermaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,ordermaster);
        OrderMaster updateResult = orderMasterDao.save(ordermaster);
        if(updateResult == null){
            throw new SellException(ResultEnum.order_update_fail);
        }
        return orderDTO;
    }
}
