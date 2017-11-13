package com.ymd.ddshop.service.impl;

import com.ymd.ddshop.common.dto.TreeNode;
import com.ymd.ddshop.dao.TbItemCatMapper;
import com.ymd.ddshop.pojo.po.TbItemCat;
import com.ymd.ddshop.pojo.po.TbItemCatExample;
import com.ymd.ddshop.pojo.po.TbItemExample;
import com.ymd.ddshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemCatMapper itemCatdao;

    @Override
    public List<TreeNode> listItemCatsByPid(Long parentId) {
        List<TreeNode> treeNodelist = null;
        try {
            TbItemCatExample example = new TbItemCatExample();
            TbItemCatExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentId);

            List<TbItemCat> itemCatList = itemCatdao.selectByExample(example);
            treeNodelist = new ArrayList<TreeNode>();
            for (int i=0;i<itemCatList.size();i++){
                TbItemCat itemCat = itemCatList.get(i);
                TreeNode treeNode = new TreeNode();
                treeNode.setId(itemCat.getId());
                treeNode.setText(itemCat.getName());
                treeNode.setState(itemCat.getIsParent()? "closed":"open");

                treeNodelist.add(treeNode);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return treeNodelist;
    }
}
