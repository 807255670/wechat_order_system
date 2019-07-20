package com.hodor.demo.controller;

import com.hodor.demo.dataobject.SellerInfo;
import com.hodor.demo.enums.ResultEnum;
import com.hodor.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created By Fan Huiliang
 * 2019-07-20 20:15
 */
@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;
    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/login")
    public String login(){
        return "redirect:"+"https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?"+
                "app_id=2019071465849264&scope=auth_user&redirect_uri="+
                "http://4xejbw.natappfree.cc/wechat_order/alipay/auth";
    }

    @GetMapping("/auth")
    public ModelAndView auth(HttpServletResponse response,
                              HttpServletRequest request,
                              Map<String,Object> map){

        //去和数据库里userid做匹配
        String userid = request.getAttribute("userid").toString();
        System.out.println("login:"+userid);
        SellerInfo sellerInfo = sellerService.findSellerInfoByUserid(userid);
        if(sellerInfo == null){
            map.put("msg", ResultEnum.login_fail.getMessage());
            map.put("url","/wechat_order/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        //设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = 7200;//2h
        redisTemplate.opsForValue().set("token_"+token,userid,expire, TimeUnit.SECONDS);
        System.out.println("redis设置完毕");
        //设置token至cookie
        Cookie cookie = new Cookie("token",token);
        cookie.setPath("/");
        cookie.setMaxAge(7200);
        response.addCookie(cookie);
        System.out.println("cookie设置完毕");
        return new ModelAndView("redirect:/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String,Object> map){
        Cookie tokenCookie=null;
        for(Cookie cookie:request.getCookies()){
            if("token".equals(cookie.getName())){
                tokenCookie = cookie;
            }
        }
        if(tokenCookie != null){
            //清除redis
            redisTemplate.opsForValue().getOperations().delete("token_"+tokenCookie.getValue());

            //清除token
            Cookie cookie = new Cookie("token",null);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        map.put("msg",ResultEnum.logout_success.getMessage());
        map.put("url","/wechat_order/seller/login");
        return new ModelAndView("common/success",map);
    }

}
