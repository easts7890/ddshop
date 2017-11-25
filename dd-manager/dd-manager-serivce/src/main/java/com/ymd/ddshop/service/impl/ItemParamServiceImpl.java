package com.ymd.ddshop.service.impl;

import com.ymd.ddshop.common.dto.Page;
import com.ymd.ddshop.common.dto.Result;
import com.ymd.ddshop.dao.TbItemParamCustomMapper;
import com.ymd.ddshop.dao.TbItemParamMapper;
import com.ymd.ddshop.pojo.po.TbItemParam;
import com.ymd.ddshop.pojo.po.TbItemParamExample;
import com.ymd.ddshop.pojo.vo.TbItemParamCustom;
import com.ymd.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemParamCustomMapper itemParamCustomDao;

    @Autowired
    private TbItemParamMapper itemParamDao;

    @Override
    public Result<TbItemParamCustom> listItemParamsByPage(Page page) {
        Result<TbItemParamCustom> result = null;

        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("page",page);
            int count = itemParamCustomDao.countItemParams();

            List<TbItemParamCustom> list = itemParamCustomDao.listItemParamsByPage(map);

            result = new Result<TbItemParamCustom>();
            result.setTotal(count);
            result.setRows(list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public TbItemParam getItemParamByid(Long cid) {
        TbItemParam tbItemParam = null;
        try {
            TbItemParamExample example = new TbItemParamExample();
            TbItemParamExample.Criteria criteria = example.createCriteria();
            criteria.andItemCatIdEqualTo(cid);

            List<TbItemParam> list = itemParamDao.selectByExampleWithBLOBs(example);
            if(list !=null && list.size()>0){
                tbItemParam = list.get(0);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return tbItemParam;
    }
}
