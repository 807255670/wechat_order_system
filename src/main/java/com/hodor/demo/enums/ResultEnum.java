package com.hodor.demo.enums;


import lombok.Getter;

/**
 * Created By Fan Huiliang
 * 2019-07-08 21:05
 */
@Getter
public enum ResultEnum {

    product_not_exit(10,"商品不存在"),
    product_stock_error(1,"库存不足")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}


