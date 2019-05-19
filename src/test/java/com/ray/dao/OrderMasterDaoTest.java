package com.ray.dao;

import com.ray.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    private final  String OPENID="110110";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234569");
        orderMaster.setBuyerAddress("nn");
        orderMaster.setBuyerName("cc");
        orderMaster.setBuyerPhone("123456782910");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.1));

        Assert.assertNotNull( orderMasterDao.save(orderMaster));
    }

    @Test
    public void findByBuyerOpenid()throws  Exception{
        PageRequest request = PageRequest.of(1,3);
        Page<OrderMaster> result = orderMasterDao.findByBuyerOpenid(OPENID,request);
        System.out.println(result.getTotalElements());
    }
}