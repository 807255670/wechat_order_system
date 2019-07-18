package com.hodor.demo.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hodor.demo.enums.ProductStatus;
import com.hodor.demo.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * @author Fan Huiliang
 * @date 2019-07-07 10:40
 */

@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    /*库存*/
    private Integer productStock;

    private String productDescription;

    /*小图*/
    private String productIcon;

    /*状态  0正常 1下架*/
    private Integer productStatus;

    /*类目编号*/
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatus getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatus.class);
    }
}
