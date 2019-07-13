package com.hodor.demo.enums;


import lombok.Getter;

/**
 * Created By Fan Huiliang
 * 2019-07-08 21:05
 */
@Getter
public enum ResultEnum {

    product_not_exit(10,"商品不存在"),
    product_stock_error(1,"库存不足"),
    order_not_exit(2,"订单不存在"),
    order_detail_not_exit(3,"订单详情不存在"),
    order_status_error(4,"订单状态错误"),
    order_update_fail(5,"订单更新失败"),
    order_detail_empty(6,"订单中无商品")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}


