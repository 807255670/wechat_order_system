package com.hodor.demo.controller;

import com.hodor.demo.enums.ResultEnum;
import com.hodor.demo.service.impl.AlipayLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created By Fan Huiliang
 * 2019-07-14 15:01
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController {

    @Autowired
    private AlipayLoginService alipayLoginService;

    @RequestMapping("/auth")
    public ModelAndView getAuthCode(HttpServletRequest request,HttpServletResponse response,
                                    Map<String,Object> map) throws ServletException, IOException {
        System.out.println("支付宝返回信息....................................................");
        //从request中获取授权信息
        String authCode = request.getParameter("auth_code");

        if (!StringUtils.isEmpty(authCode)) {
            //获取userid
            String userid = alipayLoginService.getUserid(authCode);
            System.out.println("userid: "+userid);
            request.setAttribute("userid",userid);
            request.getRequestDispatcher("/seller/auth").forward(request,response);
        }
        map.put("msg", ResultEnum.auth_error.getMessage());
        map.put("url","/wechat_order/seller/login");
        return new ModelAndView("common/error",map);
    }
}

