package com.hodor.demo.service;

import com.hodor.demo.dataobject.ProductInfo;
import com.hodor.demo.dto.CartDTO;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Created By Fan Huiliang
 * 2019-07-07 11:07
 */
public interface ProductService {


    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
