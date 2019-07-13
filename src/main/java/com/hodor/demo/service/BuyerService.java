package com.hodor.demo.service;

import com.hodor.demo.dto.OrderDTO;

/**
 * Created By Fan Huiliang
 * 2019-07-13 22:47
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
