package com.ymd.ddshop.web;

import com.ymd.ddshop.common.dto.Order;
import com.ymd.ddshop.common.dto.Page;
import com.ymd.ddshop.common.dto.Result;
import com.ymd.ddshop.pojo.po.TbItem;
import com.ymd.ddshop.pojo.vo.TbItemCustom;
import com.ymd.ddshop.pojo.vo.TbItemQuery;
import com.ymd.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Scope("prototype")
public class ItemAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/item/{itemId}",method = RequestMethod.GET)
    public TbItem getById(@PathVariable("itemId") long itemId){
        TbItem tbItem = itemService.getById(itemId);
        return tbItem;
    }

    @RequestMapping("/{page}")
    public String page(@PathVariable("page") String page){

        return page;
    }

    /*@ResponseBody
    @RequestMapping("/items")
    public List<TbItem> listItem(){
        List<TbItem> list =null;
        try {
            list =itemService.listItems();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return list;
    }*/
    @ResponseBody
    @RequestMapping("/items")
    public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query){
        Result<TbItemCustom> list=null;
        try {
            list=itemService.listItemsByPage(page,order,query);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return list;
    }

    @ResponseBody
    @RequestMapping("/items/batch")
    public int updateBatch(@RequestParam("ids[]")List<Long> ids){
        int i = 0;
        try {
            i=itemService.updateBatch(ids);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }
}

