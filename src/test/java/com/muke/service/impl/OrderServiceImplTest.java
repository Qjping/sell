package com.muke.service.impl;

import com.muke.dataobject.OrderDetail;
import com.muke.dataobject.OrderMaster;
import com.muke.dto.OrderDTO;
import com.muke.enmu.OrderStatusEnum;
import com.muke.repositor.OrderMasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private final  String BUYER_OPENID="1101110";
    private final  String ORDER_ID="1563803667654722032";
    @Test
    public void create() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerAddress("北京");
        orderDTO.setBuyerName("qiujingping");
        orderDTO.setBuyerPhone("1234567800");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail>orderDetailList=new ArrayList<>();
        OrderDetail o1=new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(2);


        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result={}",result);
    }

    @Test
    public void findOne() {

        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】:"+result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());

    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0,2);
        Page<OrderMaster>orderMastersPage=orderMasterRepository.findByBuyerOpenid(BUYER_OPENID,request);
        Assert.assertNotEquals(0,orderMastersPage.getTotalElements());
    }

    @Test
    public void cancel() {

        OrderDTO orderDTO=orderService.findOne(ORDER_ID);
        OrderDTO result=orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}