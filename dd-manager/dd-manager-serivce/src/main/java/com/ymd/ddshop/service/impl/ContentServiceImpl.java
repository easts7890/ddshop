package com.ymd.ddshop.service.impl;

import com.ymd.ddshop.common.jedis.JedisClient;
import com.ymd.ddshop.common.util.JsonUtils;
import com.ymd.ddshop.dao.TbContentMapper;
import com.ymd.ddshop.pojo.po.TbContent;
import com.ymd.ddshop.pojo.po.TbContentExample;
import com.ymd.ddshop.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbContentMapper contentDao;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public List<TbContent> listContentsByCid(Long id) {
        List<TbContent> list = null;
        try {
            String json = jedisClient.hget("CONTENT_LIST",id+"");
            if(StringUtils.isNotBlank(json)){
                return JsonUtils.jsonToList(json, TbContent.class);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(id);

        list = contentDao.selectByExample(example);
        try {
            jedisClient.hset("CONTENT_LIST",id+"", JsonUtils.objectToJson(list));
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return list;
    }
}
