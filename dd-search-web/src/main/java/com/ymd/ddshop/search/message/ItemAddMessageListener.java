package com.ymd.ddshop.search.message;

import com.ymd.ddshop.dao.TbItemSearchCustomMapper;
import com.ymd.ddshop.pojo.vo.TbItemSearchCustom;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ItemAddMessageListener implements MessageListener {

    @Autowired
    private TbItemSearchCustomMapper tbItemSearchCustomDao;
    @Autowired
    private SolrServer solrServer;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void onMessage(Message message) {
        try{
            //接收消息
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            Long itemId = Long.parseLong(text);

            //按照ID查询指定商品
            TbItemSearchCustom tbItemSearchCustom = tbItemSearchCustomDao.getById(itemId);
           //添加到文档域
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",tbItemSearchCustom.getId());
            document.addField("item_category_name",tbItemSearchCustom.getCatName());
            document.addField("item_title",tbItemSearchCustom.getTitle());
            document.addField("item_sell_point",tbItemSearchCustom.getSellPoint());
            document.addField("item_price",tbItemSearchCustom.getPrice());
            document.addField("item_image",tbItemSearchCustom.getImage());

            //添加到索引库
            solrServer.add(document);
            solrServer.commit();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
    }
}
