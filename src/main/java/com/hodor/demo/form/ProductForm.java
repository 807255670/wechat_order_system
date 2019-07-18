package com.hodor.demo.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created By Fan Huiliang
 * 2019-07-18 23:01
 */
@Data
public class ProductForm {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    /*库存*/
    private Integer productStock;

    private String productDescription;

    /*小图*/
    private String productIcon;

    /*类目编号*/
    private Integer categoryType;
}
