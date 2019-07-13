package com.hodor.demo.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created By Fan Huiliang
 * 2019-07-13 17:53
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "微信号必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;

}
