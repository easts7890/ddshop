package com.ymd.ddshop.service.impl;

import com.ymd.ddshop.dao.TbItemDescMapper;
import com.ymd.ddshop.pojo.po.TbItemDesc;
import com.ymd.ddshop.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemDescServiceImpl  implements ItemDescService {
    @Autowired
    private TbItemDescMapper tbItemDescDao;
    @Override
    public TbItemDesc getByItemIb(Long itemId) {
        TbItemDesc desc = null;
        try {
             desc = tbItemDescDao.selectByPrimaryKey(itemId);

        }catch (Exception e){
            e.printStackTrace();
        }
        return desc;
    }
}
