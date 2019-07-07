package com.hodor.demo.service.impl;

import com.hodor.demo.dataobject.ProductInfo;
import com.hodor.demo.enums.ProductStatus;
import com.hodor.demo.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo p = productService.findOne("123456");

        System.out.println(p.toString());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> pList = productService.findUpAll();
        System.out.println(pList.size());
    }

    @Test
    public void findAll() {

        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() {

        ProductInfo p = new ProductInfo();
        p.setProductId("123457");
        p.setProductName("皮皮吓");
        p.setProductPrice(new BigDecimal(3.2));
        p.setProductStock(100);
        p.setProductDescription("很好喝的虾");
        p.setProductIcon("http://xxx.jpg");
        p.setProductStatus(ProductStatus.DOWN.getCode());
        p.setCategoryType(2);

        ProductInfo result = productService.save(p);

        System.out.println(result.toString());
    }
}