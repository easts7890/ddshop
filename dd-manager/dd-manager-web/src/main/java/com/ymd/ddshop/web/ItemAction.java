package com.ymd.ddshop.web;

import com.ymd.ddshop.pojo.po.TbItem;
import com.ymd.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
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
    }
}
