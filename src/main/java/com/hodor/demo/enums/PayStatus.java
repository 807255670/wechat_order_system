package com.hodor.demo.enums;

import lombok.Getter;

/**
 * Created By Fan Huiliang
 * 2019-07-08 19:46
 */
@Getter
public enum PayStatus {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");


    private Integer code;

    private String message;

    PayStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}


