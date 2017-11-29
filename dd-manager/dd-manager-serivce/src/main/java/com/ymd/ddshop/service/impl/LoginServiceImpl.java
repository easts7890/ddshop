package com.ymd.ddshop.service.impl;

import com.ymd.ddshop.common.dto.MessageResult;
import com.ymd.ddshop.common.jedis.JedisClient;
import com.ymd.ddshop.common.util.JsonUtils;
import com.ymd.ddshop.dao.TbUserMapper;
import com.ymd.ddshop.pojo.po.TbUser;
import com.ymd.ddshop.pojo.po.TbUserExample;
import com.ymd.ddshop.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbUserMapper userDao;
    @Autowired
    private JedisClient jedisClient;
    @Override
    public MessageResult userLogin(String username, String password) {
        MessageResult mr = new MessageResult();
        try {
            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria  criteria = example.createCriteria();
            criteria.andUsernameEqualTo(username);

            List<TbUser> list = userDao.selectByExample(example);
            if(list == null || list.size() ==0){
                mr.setSuccess(false);
                mr.setMessage("用户名不存在");

                return mr;
            }
            TbUser user = list.get(0);
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            if(!md5Password.equals(user.getPassword())){
                mr.setSuccess(false);
                mr.setMessage("用户名或密码错误");
                return mr;
            }

            String token = UUID.randomUUID().toString();

            user.setPassword(null);
            jedisClient.set("TT_token"+ token, JsonUtils.objectToJson(user));
            jedisClient.expire("TT_token" + token,1800);

            mr.setSuccess(true);
            mr.setMessage("登陆成功");
            mr.setData(token);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return mr;
    }
}
