package com.ymd.ddshop.dao;

import com.ymd.ddshop.common.dto.Order;
import com.ymd.ddshop.common.dto.Page;
import com.ymd.ddshop.pojo.po.TbItem;
import com.ymd.ddshop.pojo.vo.TbItemCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbItemCustomMapper {
    int countItems(Map<String,Object> map);

    List<TbItemCustom> listItemsByPage(Map<String,Object> map);
}
