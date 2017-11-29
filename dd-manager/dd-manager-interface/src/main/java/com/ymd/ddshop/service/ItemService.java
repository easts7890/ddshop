package com.ymd.ddshop.service;

import com.ymd.ddshop.common.dto.Order;
import com.ymd.ddshop.common.dto.Page;
import com.ymd.ddshop.common.dto.Result;
import com.ymd.ddshop.pojo.po.TbItem;
import com.ymd.ddshop.pojo.vo.TbItemCustom;
import com.ymd.ddshop.pojo.vo.TbItemQuery;

import java.util.List;

public interface ItemService {
    TbItem getById(Long itemId);

    List<TbItem> listItems();

    Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query);

    int updateBatch(List<Long> ids);

    Long saveItem(TbItem tbItem, String content,String paramData);
}
