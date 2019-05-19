package com.ray.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerServiceImplTest {

    private  static  final  String openid="abc";

    @Autowired
    private  SellerServiceImpl sellerService;

    @Test
    public void findSellerInfoByOpenid() {
        Assert.assertEquals(openid,sellerService.findSellerInfoByOpenid(openid).getOpenid());
    }
}