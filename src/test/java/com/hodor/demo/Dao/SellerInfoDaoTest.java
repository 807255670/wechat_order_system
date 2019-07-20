package com.hodor.demo.Dao;

import com.hodor.demo.dataobject.SellerInfo;
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
    private SellerInfoDao sellerInfoDao;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId("1");
        sellerInfo.setUsername("123456");
        sellerInfo.setPassword("123456");
        sellerInfo.setUserid("123456");
        sellerInfoDao.save(sellerInfo);
    }

    @Test
    public void findByUserid() {
        SellerInfo sellerInfo = sellerInfoDao.findByUserid("123456");
        System.out.println(sellerInfo.getUsername());
    }
}