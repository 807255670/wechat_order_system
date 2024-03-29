package com.hodor.demo.enums;


import lombok.Getter;

/**
 * Created By Fan Huiliang
 * 2019-07-08 21:05
 */
@Getter
public enum ResultEnum {

    success(666,"成功"),
    param_error(0,"参数错误"),
    product_stock_error(1,"库存不足"),
    order_not_exit(2,"订单不存在"),
    order_detail_not_exit(3,"订单详情不存在"),
    order_status_error(4,"订单状态错误"),
    order_update_fail(5,"订单更新失败"),
    order_detail_empty(6,"订单中无商品"),
    order_pay_status_error(7,"订单支付状态不正确"),
    cart_empty(8,"购物车为空"),
    order_owner_error(9,"订单不属于该用户"),
    product_not_exit(10,"商品不存在"),
    order_cancel_success(11,"订单取消成功"),
    order_finish_success(12,"订单完结成功"),
    product_status_error(13,"商品状态错误"),
    login_fail(14,"登录失败,用户未注册"),
    logout_success(15,"登出成功"),
    auth_error(16,"授权失败")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}


