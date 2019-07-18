package com.hodor.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hodor.demo.dataobject.OrderDetail;
import com.hodor.demo.enums.OrderStatus;
import com.hodor.demo.enums.PayStatus;
import com.hodor.demo.utils.EnumUtil;
import com.hodor.demo.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created By Fan Huiliang
 * 2019-07-08 20:40
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) 字段内容为null不返回
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatus.NEW.getCode();

    private Integer payStatus = PayStatus.WAIT.getCode();

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatus getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatus.class);
    }

    @JsonIgnore
    public PayStatus getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatus.class);
    }

}
