package com.ymd.ddshop.service;

import com.ymd.ddshop.pojo.vo.TbSearchItemResult;

public interface SearchItemService {
    boolean importAllItems();

    TbSearchItemResult search(String keyword, int page, int rows);
}
