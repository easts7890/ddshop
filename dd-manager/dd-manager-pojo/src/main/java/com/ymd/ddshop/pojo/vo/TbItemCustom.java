package com.ymd.ddshop.pojo.vo;

import com.ymd.ddshop.pojo.po.TbItem;

public class TbItemCustom extends TbItem {

    private String catName;

    public String getCatName(){
        return catName;
    }

    public void setCatName(String catName){
        this.catName=catName;
    }
}
