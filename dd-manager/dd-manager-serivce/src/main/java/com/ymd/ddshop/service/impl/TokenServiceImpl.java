package com.ymd.ddshop.service.impl;

import com.ymd.ddshop.common.dto.MessageResult;
import com.ymd.ddshop.common.jedis.JedisClient;
import com.ymd.ddshop.common.util.JsonUtils;
import com.ymd.ddshop.pojo.po.TbUser;
import com.ymd.ddshop.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisClient jedisClient;
    @Override
    public MessageResult getUserByToken(String token) {
        MessageResult mr = new MessageResult();
        try {
            String userJson = jedisClient.get("TT_TOKEN" + token);
            if(StringUtils.isBlank(userJson)){
                mr.setSuccess(false);
                mr.setMessage("登陆已过期");
                return mr;
            }
            jedisClient.expire("TT_TOKEN" + token,1800);
            TbUser tbUser = JsonUtils.jsonToPojo(userJson, TbUser.class);
            mr.setSuccess(true);
            mr.setMessage("欢迎回来");
            mr.setData(tbUser);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return mr;
    }
}
