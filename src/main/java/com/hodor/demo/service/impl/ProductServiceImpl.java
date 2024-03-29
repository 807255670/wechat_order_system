package com.hodor.demo.service.impl;

import com.hodor.demo.Dao.ProductInfoDao;
import com.hodor.demo.dataobject.ProductInfo;
import com.hodor.demo.dto.CartDTO;
import com.hodor.demo.enums.ProductStatus;
import com.hodor.demo.enums.ResultEnum;
import com.hodor.demo.exception.SellException;
import com.hodor.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = productInfoDao.
                    findById(cartDTO.getProductId()).get();
            if(productInfo == null){
                throw new SellException(ResultEnum.product_not_exit);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = productInfoDao.getOne(cartDTO.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.product_not_exit);
            }
            int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result<0){
                throw new SellException(ResultEnum.product_stock_error);
            }
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo;
        try{
            productInfo = productInfoDao.findById(productId).get();
        }catch (Exception e){
            throw new SellException(ResultEnum.product_not_exit);
        }
        if(productInfo.getProductStatus() != ProductStatus.DOWN.getCode()){
            throw new SellException(ResultEnum.product_status_error);
        }
        //更新
        productInfo.setProductStatus(ProductStatus.UP.getCode());
        return productInfoDao.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo;
        try{
            productInfo = productInfoDao.findById(productId).get();
        }catch (Exception e){
            throw new SellException(ResultEnum.product_not_exit);
        }
        if(productInfo.getProductStatus() == ProductStatus.DOWN.getCode()){
            throw new SellException(ResultEnum.product_status_error);
        }
        //更新
        productInfo.setProductStatus(ProductStatus.DOWN.getCode());
        return productInfoDao.save(productInfo);
    }
}
