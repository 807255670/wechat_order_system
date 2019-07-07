package com.hodor.demo.service.impl;

import com.hodor.demo.Dao.ProductInfoDao;
import com.hodor.demo.dataobject.ProductInfo;
import com.hodor.demo.enums.ProductStatus;
import com.hodor.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Created By Fan Huiliang
 * 2019-07-07 11:12
 */

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatus.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }
}