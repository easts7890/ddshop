package com.ymd.ddshop.web;

import com.ymd.ddshop.common.dto.TreeNode;
import com.ymd.ddshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping("/itemCats")
    public List<TreeNode> listItemCatByPid(@RequestParam("parentId") Long parent){
        List<TreeNode> treeNodeList = null;
        try{
            treeNodeList = itemCatService.listItemCatsByPid(parent);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return treeNodeList;
    }
}
