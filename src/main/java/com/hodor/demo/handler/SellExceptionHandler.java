package com.hodor.demo.handler;

import com.hodor.demo.exception.SellAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created By Fan Huiliang
 * 2019-07-21 21:48
 */
@ControllerAdvice
public class SellExceptionHandler {

    //拦截登录异常
    @ExceptionHandler(value = SellAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:/seller/login");
    }

}
