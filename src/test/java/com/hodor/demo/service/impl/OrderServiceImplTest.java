package com.hodor.demo.service.impl;

import com.hodor.demo.dataobject.OrderDetail;
import com.hodor.demo.dto.OrderDTO;
import com.hodor.demo.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("农行");
        orderDTO.setBuyerName("袁金海");
        orderDTO.setBuyerPhone("15600000000");
        orderDTO.setBuyerOpenid("5hhh6dss");


        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1007");
        o1.setProductQuantity(2);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("1006");
        o2.setProductQuantity(1);

        OrderDetail o3 = new OrderDetail();
        o3.setProductId("1005");
        o3.setProductQuantity(5);

        OrderDetail o4 = new OrderDetail();
        o4.setProductId("1004");
        o4.setProductQuantity(4);

        orderDTO.setOrderDetailList(Arrays.asList(o1,o2,o3,o4));
        orderService.create(orderDTO);
        orderDTO.setOrderDetailList(Arrays.asList(o1,o2,o3));
        orderService.create(orderDTO);
        orderDTO.setOrderDetailList(Arrays.asList(o1,o4));
        orderService.create(orderDTO);
        orderDTO.setOrderDetailList(Arrays.asList(o2,o3));
        orderService.create(orderDTO);
        orderDTO.setOrderDetailList(Arrays.asList(o2,o3,o4));
        orderService.create(orderDTO);
    }

    @Test
    public void findOne() {

        OrderDTO orderDTO = orderService.findOne("123456");

    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(
                "18851858338",pageRequest);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne("123456");
        OrderDTO result = orderService.cancel(orderDTO);
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne("1562596365620381815");
        OrderDTO result = orderService.finish(orderDTO);
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne("1562596466343119504");
        OrderDTO result = orderService.paid(orderDTO);
    }

    @Test
    public void list(){
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        System.out.println(orderDTOPage.getTotalElements());
    }
}