package com.ymd.ddshop.dao;

import com.ymd.ddshop.pojo.po.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao-text.xml"})
public class TbUserMapperTest {

    @Autowired
    private  TbUserMapper userDao;
    @Test
    public void selectByPrimaryKey() throws Exception {
        TbUser tbUser = userDao.selectByPrimaryKey(5L);
        System.out.println(tbUser);
    }
    
    @Test
    public void textString(){
        String name = "123.jpg";
        System.out.print(name.substring(name.lastIndexOf(".")));
    }

}