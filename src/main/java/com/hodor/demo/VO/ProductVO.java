package com.hodor.demo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品 包含类目
 * Created By Fan Huiliang
 * 2019-07-07 12:03
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categotyType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;


}
