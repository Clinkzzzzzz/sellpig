package com.ray.dao;

import com.ray.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234522");
        orderDetail.setProductQuantity(123);
        orderDetail.setOrderId("123111");
        orderDetail.setProductIcon("123213");
        orderDetail.setProductId("1111212");
        orderDetail.setProductName("盖饭");
        orderDetail.setProductPrice(new BigDecimal(3.2));

        Assert.assertNotNull(orderDetailDao.save(orderDetail));
    }

    @Test
    public void findByOrderId()throws  Exception{
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId("123111");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}