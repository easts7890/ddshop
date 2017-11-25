package com.ymd.ddshop.web;

import com.ymd.ddshop.common.dto.MessageResult;
import com.ymd.ddshop.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchItemAction {

    @Autowired
    private SearchItemService searchItemService;

    @ResponseBody
    @RequestMapping("/item/search/import")
    public MessageResult searchItemIndex(){
        boolean bool = searchItemService.importAllItems();
        MessageResult mr = new MessageResult();
        if (bool){
            mr.setSuccess(true);
            mr.setMessage("索引导入成功");
        }else {
            mr.setMessage("素银导入失败");
            mr.setSuccess(false);
        }
        return mr;
    }
}
