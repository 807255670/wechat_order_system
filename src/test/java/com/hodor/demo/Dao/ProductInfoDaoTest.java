package com.hodor.demo.Dao;

import com.hodor.demo.dataobject.ProductInfo;
import org.junit.After;
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
public class ProductInfoDaoTest {

    @Autowired
    ProductInfoDao productInfoDao;

    @Test
    public void saveTest(){
        ProductInfo p = new ProductInfo();
        p.setProductId("123456");
        p.setProductName("皮蛋粥");
        p.setProductPrice(new BigDecimal(3.2));
        p.setProductStock(100);
        p.setProductDescription("很好喝的粥");
        p.setProductIcon("http://xxx.jpg");
        p.setProductStatus(0);
        p.setCategoryType(1);

        ProductInfo result = productInfoDao.save(p);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> p = productInfoDao.findByProductStatus(0);
        Assert.assertNotNull(p);

    }
}