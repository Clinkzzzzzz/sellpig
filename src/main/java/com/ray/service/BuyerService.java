package com.ray.service;

import com.ray.dto.OrderDTO;

/**
 * 买家service
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);
    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
