package com.ymd.ddshop.sso.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SSOInexAction {

    @RequestMapping("/")
    public String index(){
        return "login";
    }
}
