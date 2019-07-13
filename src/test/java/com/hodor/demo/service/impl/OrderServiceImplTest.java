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
        orderDTO.setBuyerAddress("徐家汇");
        orderDTO.setBuyerName("fhl");
        orderDTO.setBuyerPhone("465");
        orderDTO.setBuyerOpenid("123456");

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(2);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("1234567");
        o2.setProductQuantity(3);
        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);
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
    }

    @Test
    public void paid() {
    }
}