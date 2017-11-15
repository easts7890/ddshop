package com.ymd.ddshop.dao;

import com.ymd.ddshop.pojo.vo.TbItemParamCustom;

import java.util.List;
import java.util.Map;

public interface TbItemParamCustomMapper {
    int countItemParams();

    List<TbItemParamCustom> listItemParamsByPage(Map<String,Object> map);
}
