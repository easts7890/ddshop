package com.ymd.ddshop.portal.web;

import com.ymd.ddshop.common.util.PropKit;
import com.ymd.ddshop.pojo.po.TbContent;
import com.ymd.ddshop.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PortalIndexAction {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/")
    public String porlIndex(Model model){

        Long id = PropKit.use("ftp.properties").getLong("ftp.gallery");
        List<TbContent> list = contentService.listContentsByCid(id);

        model.addAttribute("adlist",list);
        return "index";
    }
}
