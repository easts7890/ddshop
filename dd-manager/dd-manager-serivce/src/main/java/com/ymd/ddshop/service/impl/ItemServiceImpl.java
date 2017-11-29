package com.ymd.ddshop.service.impl;

import com.ymd.ddshop.common.dto.Order;
import com.ymd.ddshop.common.dto.Page;
import com.ymd.ddshop.common.dto.Result;
import com.ymd.ddshop.common.util.IDUtils;
import com.ymd.ddshop.dao.TbItemCustomMapper;
import com.ymd.ddshop.dao.TbItemDescMapper;
import com.ymd.ddshop.dao.TbItemMapper;
import com.ymd.ddshop.dao.TbItemParamItemMapper;
import com.ymd.ddshop.pojo.po.TbItem;
import com.ymd.ddshop.pojo.po.TbItemDesc;
import com.ymd.ddshop.pojo.po.TbItemExample;
import com.ymd.ddshop.pojo.po.TbItemParamItem;
import com.ymd.ddshop.pojo.vo.TbItemCustom;
import com.ymd.ddshop.pojo.vo.TbItemQuery;
import com.ymd.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemMapper itemDao;

    @Autowired
    private TbItemCustomMapper itemCustomDao;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemDao;

    @Override
    public TbItem getById(Long itemId) {
        TbItem tbItem = itemDao.selectByPrimaryKey(itemId);
        return tbItem;
    }

    @Override
    public List<TbItem> listItems() {
        List<TbItem> list = null;
        try {
            list = itemDao.selectByExample(null);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Result<TbItemCustom> listItemsByPage(Page page, Order order,TbItemQuery query) {
        Result<TbItemCustom> result = null;
        try {
            Map<String,Object> map = new HashMap();
            map.put("page",page);
            map.put("order",order);
            map.put("query",query);

            result = new Result<TbItemCustom>();

            int total = itemCustomDao.countItems(map);
            result.setTotal(total);

            List<TbItemCustom> list = itemCustomDao.listItemsByPage(map);
            result.setRows(list);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateBatch(List<Long> ids) {
        int i =0;
        try {
            TbItem record = new TbItem();
            record.setStatus((byte)3);

            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);

             i = itemDao.updateByExampleSelective(record, example);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @Transactional
    @Override
    public Long saveItem(TbItem tbItem, String content,String paramData) {
        Long itemId = null;
        try {
            //tb_iteme
            itemId = IDUtils.getItemId();
            tbItem.setId(itemId);
            tbItem.setStatus((byte)2);
            tbItem.setCreated(new Date());
            tbItem.setUpdated(new Date());

            int i = itemDao.insert(tbItem);

            //tb_item_desc
            TbItemDesc desc =new TbItemDesc();
            desc.setItemId(itemId);
            desc.setItemDesc(content);
            desc.setCreated(new Date());
            desc.setUpdated(new Date());

            i+= itemDescMapper.insert(desc);

            TbItemParamItem tbItemParamItem = new TbItemParamItem();
            tbItemParamItem.setItemId(itemId);
            tbItemParamItem.setParamData(paramData);
            tbItemParamItem.setCreated(new Date());
            tbItemParamItem.setUpdated(new Date());
            i += itemParamItemDao.insert(tbItemParamItem);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return itemId;
    }
}
