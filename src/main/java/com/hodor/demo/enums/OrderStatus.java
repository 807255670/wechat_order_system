package com.hodor.demo.enums;

import lombok.Getter;

/**
 * Created By Fan Huiliang
 * 2019-07-08 19:41
 */
@Getter
public enum OrderStatus {
    NEW(0,"新订单"),
    FINISH(1,"完结"),
    CANCEL(2,"已取消");

    private Integer code;

    private String message;

    OrderStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}


