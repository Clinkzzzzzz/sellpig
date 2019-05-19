package com.ray.service.impl;

import com.ray.dto.OrderDTO;
import com.ray.enums.ResultEnum;
import com.ray.exception.SellException;
import com.ray.service.BuyerService;
import com.ray.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO =checkOrderOwner(openid,orderId);
        if(orderDTO==null){
            log.error("【取消订单】抽到该订单，orderId={}",orderId);
            throw  new SellException(ResultEnum.ORDER_NO_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private  OrderDTO checkOrderOwner(String openid,String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO==null){
            return  null;
        }
        //判断订单是否属于自己
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】订单的openid不止一只，openid={},orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
