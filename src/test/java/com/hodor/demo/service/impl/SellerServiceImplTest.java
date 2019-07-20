package com.hodor.demo.service.impl;

import com.hodor.demo.Dao.SellerInfoDao;
import com.hodor.demo.service.SellerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {
    @Autowired
    SellerService sellerService;

    @Test
    public void findSellerInfoByUserid() {
        System.out.println(sellerService.findSellerInfoByUserid("123456"));
    }
}