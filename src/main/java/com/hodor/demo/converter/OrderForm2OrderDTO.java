package com.hodor.demo.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hodor.demo.dataobject.OrderDetail;
import com.hodor.demo.dto.OrderDTO;
import com.hodor.demo.enums.ResultEnum;
import com.hodor.demo.exception.SellException;
import com.hodor.demo.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Fan Huiliang
 * 2019-07-13 18:06
 */
@Slf4j
public class OrderForm2OrderDTO {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("对象转换错误");
            throw new SellException(ResultEnum.param_error);

        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
