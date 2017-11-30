package com.ymd.ddshop.item.message;

import com.ymd.ddshop.pojo.po.TbItem;
import com.ymd.ddshop.pojo.po.TbItemDesc;
import com.ymd.ddshop.service.ItemDescService;
import com.ymd.ddshop.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class GenerateHTMLListener implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;
        @Autowired
    private ItemDescService itemDescService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Override
    public void onMessage(Message message) {
        try {
            //
            TextMessage textMessage = (TextMessage) message;
            String test = textMessage.getText();

            long itemId = Long.parseLong(test);
            TbItem item = itemService.getById(itemId);

            TbItemDesc desc = itemDescService.getByItemIb(itemId);

            Map<String ,Object> map = new HashMap<>();
            map.put("item",item);
            map.put("itemDesc",desc);

            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");

            Writer out = new FileWriter("g:/ft/"+itemId+".html");
            template.process(map,out);
            out.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

}
