package com.ymd.ddshop.sso.web;

import com.ymd.ddshop.common.dto.MessageResult;
import com.ymd.ddshop.common.util.CookieUtils;
import com.ymd.ddshop.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SSOLoginAction {

    @Autowired
    private LoginService loginService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @ResponseBody
    @RequestMapping("/user/login")
    public MessageResult login(String username, String password, HttpServletResponse response, HttpServletRequest request){
        MessageResult mr = null;
        try {
            mr = loginService.userLogin(username,password);
            if(mr.isSuccess()){
                String token = mr.getData().toString();
                CookieUtils.setCookie(request,response,"tt_token",token);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }
}
