package com.hodor.demo.Dao;

import com.hodor.demo.dataobject.OrderDetail;
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
    OrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567890");
        orderDetail.setOrderId("1234560");
        orderDetail.setProductId("123456");
        orderDetail.setProductName("皮dan");
        orderDetail.setProductPrice(new BigDecimal(1.2));
        orderDetail.setProductIcon("http://xx.jpg");
        orderDetail.setProductQuantity(2);
        orderDetailDao.save(orderDetail);
    }
    @Test
    public void findByOrderId() {

        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId("123456");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}