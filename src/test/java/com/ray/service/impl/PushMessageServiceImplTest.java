package com.ray.service.impl;

import com.ray.dto.OrderDTO;
import com.ray.service.OrderService;
import com.ray.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//防止websocket报错
public class PushMessageServiceImplTest {

    @Autowired
    private PushMessageServiceImpl pushMessageService;

    @Autowired
    private OrderService orderService;
    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("1558176592489653827");
        pushMessageService.orderStatus(orderDTO);
    }
}