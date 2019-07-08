package com.hodor.demo.exception;

import com.hodor.demo.enums.ResultEnum;

/**
 * Created By Fan Huiliang
 * 2019-07-08 21:03
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
