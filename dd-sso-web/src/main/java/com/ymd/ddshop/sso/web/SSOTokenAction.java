package com.ymd.ddshop.sso.web;

import com.ymd.ddshop.common.dto.MessageResult;
import com.ymd.ddshop.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SSOTokenAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TokenService tokenService;

    @ResponseBody
    @RequestMapping("/user/token1/{tokenId}")
    public MessageResult token(@PathVariable("tokenId")String tokenId){
        MessageResult mr = new MessageResult();
        try {
            mr = tokenService.getUserByToken(tokenId);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }
}
