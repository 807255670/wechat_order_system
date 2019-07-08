package com.hodor.demo.dto;

import lombok.Data;

/**
 * 购物车
 * Created By Fan Huiliang
 * 2019-07-08 21:38
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
