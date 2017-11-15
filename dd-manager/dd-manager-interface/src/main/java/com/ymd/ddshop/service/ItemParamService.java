package com.ymd.ddshop.service;

import com.ymd.ddshop.common.dto.Page;
import com.ymd.ddshop.common.dto.Result;
import com.ymd.ddshop.pojo.vo.TbItemParamCustom;

public interface ItemParamService {

    Result<TbItemParamCustom> listItemParamsByPage(Page page);
}
