package com.hodor.demo.service;

import com.hodor.demo.dataobject.OrderMaster;
import com.hodor.demo.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created By Fan Huiliang
 * 2019-07-08 20:33
 */
public interface OrderService {

    //创建订单
    OrderDTO create(OrderDTO orderDTO);


    //查询单个订单
    OrderDTO findOne(String orderId);

    //查询订单列表
    Page<OrderDTO>  findList(String BuyerOpenid, Pageable pageable);

    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //完结订单
    OrderDTO finish(OrderDTO orderDTO);

    //支付订单
    OrderDTO paid(OrderDTO orderDTO);


}
