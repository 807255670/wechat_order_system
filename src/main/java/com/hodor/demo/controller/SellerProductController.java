package com.hodor.demo.controller;

import com.hodor.demo.dataobject.ProductInfo;
import com.hodor.demo.exception.SellException;
import com.hodor.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家端商品
 * Created By Fan Huiliang
 * 2019-07-18 17:23
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;
    /*
    * 商品列表
    * @Param page 第几页 从第一页开始
    * @Param size 页面大小
    * @Return
    * */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }
    /*
    * 商品上下架
    * @Param productId
    * @Param map
    * @return
    * */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try{
            productService.onSale(productId);
        }catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url","/wechat_order/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", "成功");
        map.put("url","/wechat_order/seller/product/list");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try{
            productService.offSale(productId);
        }catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url","/wechat_order/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", "成功");
        map.put("url","/wechat_order/seller/product/list");
        return new ModelAndView("common/success",map);
    }


}
