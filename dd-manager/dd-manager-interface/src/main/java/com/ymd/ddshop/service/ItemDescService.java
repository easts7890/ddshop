package com.ymd.ddshop.service;

import com.ymd.ddshop.pojo.po.TbItemDesc;

public interface ItemDescService {
    TbItemDesc getByItemIb(Long itemId);
}
