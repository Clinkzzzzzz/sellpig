package com.ray.dao;

import com.ray.Util.KeyUtil;
import com.ray.dataobject.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private  SellerInfoDao sellerInfoDao;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");

        Assert.assertNotNull( sellerInfoDao.save(sellerInfo));
    }

    @Test
    public void findByOpenid(){
        SellerInfo result = sellerInfoDao.findByOpenid("abc");
        Assert.assertEquals("abc",result.getOpenid());
    }
}