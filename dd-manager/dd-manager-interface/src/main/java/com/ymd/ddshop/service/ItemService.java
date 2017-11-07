package com.ymd.ddshop.service;

import com.ymd.ddshop.pojo.po.TbItem;

import java.util.List;

public interface ItemService {
    TbItem getById(Long itemId);

    List<TbItem> listItems();
}
