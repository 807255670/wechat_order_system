package com.hodor.demo.aspect;

import com.hodor.demo.enums.ResultEnum;
import com.hodor.demo.exception.SellAuthorizeException;
import com.hodor.demo.exception.SellException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created By Fan Huiliang
 * 2019-07-21 21:28
 */
@Aspect
@Component
public class SellerAuthorizeAspect {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.hodor.demo.controller.Seller*.*(..))"+
    "&& !execution(public  * com.hodor.demo.controller.SellerController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查cookie
        Cookie cookie = null;
        if(request.getCookies()!=null){
            for(Cookie cookieItem:request.getCookies()) {
                if("token".equals(cookieItem.getName())){
                    cookie = cookieItem;
                }
            }
        }

        if(cookie == null){
            System.out.println("cookie是null");
            throw new SellAuthorizeException();
        }

        //查redis
        String tokenValue = redisTemplate.opsForValue().get("token_"+cookie.getValue());
        if(StringUtils.isEmpty(tokenValue)){
            System.out.println("redis里没有token");
            throw new SellAuthorizeException();
        }
    }

}
