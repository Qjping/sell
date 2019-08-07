package com.muke.service.impl;

import com.muke.dto.OrderDTO;
import com.muke.enmu.ResultEnum;
import com.muke.exception.SellException;
import com.muke.service.BuyerService;
import com.muke.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class BuyerServiceImp implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {

        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO=checkOrderOwner(openid,orderId);
        if(orderDTO==null){
            log.error("[取消订单]查不到该订单,orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid,String orderId){
        OrderDTO orderDTO=orderService.findOne(orderId);
        if(orderDTO==null){
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】订单的openid不一致，openid={},orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
