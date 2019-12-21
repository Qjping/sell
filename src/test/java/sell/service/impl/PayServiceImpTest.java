package sell.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import sell.dataobject.OrderDetail;
import sell.dataobject.OrderMaster;
import sell.dto.OrderDTO;
import sell.repositor.OrderDetailRepository;
import sell.repositor.OrderMasterRepository;
import sell.service.OrderService;
import sell.service.PayService;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImpTest {
    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("123456") ;
        payService.create(orderDTO);
    }
}