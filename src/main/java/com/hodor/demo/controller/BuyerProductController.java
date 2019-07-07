package com.hodor.demo.controller;

import com.hodor.demo.VO.ProductInfoVO;
import com.hodor.demo.VO.ProductVO;
import com.hodor.demo.VO.ResultVO;
import com.hodor.demo.dataobject.ProductCategory;
import com.hodor.demo.dataobject.ProductInfo;
import com.hodor.demo.service.CategoryService;
import com.hodor.demo.service.ProductService;
import com.hodor.demo.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Fan Huiliang
 * 2019-07-07 11:47
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){

        //查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //传统方法查询所有商品的类目列表
        /*List<Integer> categoryTypeList = new ArrayList<>();
        for(ProductInfo productInfo:productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }*/

        //java8 lambda表达式方法
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e->e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService
                .findByCategoryTypeIn(categoryTypeList);


        //数据拼装
        List<ProductVO> productVOList = new ArrayList<>();

        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategotyType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType() == productCategory.getCategoryType()){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
