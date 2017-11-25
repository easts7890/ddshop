package com.ymd.ddshop.service;

import com.ymd.ddshop.pojo.po.TbContent;

import java.util.List;

public interface ContentService {

    List<TbContent> listContentsByCid(Long id);
}
