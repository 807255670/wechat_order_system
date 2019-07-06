package com.hodor.demo.Dao;

import com.hodor.demo.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findOneTest(){
        ProductCategory p = productCategoryDao.findById(1).get();
        System.out.println(p.toString());
    }

    @Test
    @Transactional /*不在数据库中保存*/
    public void saveTest(){
        ProductCategory p = productCategoryDao.findById(2).get();
        p.setCategoryType(3);
        productCategoryDao.save(p);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<ProductCategory> result = productCategoryDao.findByCategoryTypeIn(Arrays.asList(1,2,3));
        Assert.assertEquals(2,result.size());
    }

}