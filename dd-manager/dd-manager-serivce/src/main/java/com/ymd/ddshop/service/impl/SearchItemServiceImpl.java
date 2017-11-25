package com.ymd.ddshop.service.impl;

import com.ymd.ddshop.dao.SearchItemDao;
import com.ymd.ddshop.dao.TbItemSearchCustomMapper;
import com.ymd.ddshop.pojo.vo.TbItemSearchCustom;
import com.ymd.ddshop.pojo.vo.TbSearchItemResult;
import com.ymd.ddshop.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private TbItemSearchCustomMapper tbItemSearchCustomDao;

    @Autowired
    private SolrServer solrServer;

    @Autowired
    private SearchItemDao searchItemDao;

    @Override
    public boolean importAllItems() {
        List<TbItemSearchCustom> list = tbItemSearchCustomDao.listsearchItems();
        for(TbItemSearchCustom tbItemSearchCustom : list){
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",tbItemSearchCustom.getId());
            document.addField("item_category_name",tbItemSearchCustom.getCatName());
            document.addField("item_title",tbItemSearchCustom.getTitle());
            document.addField("item_sell_point",tbItemSearchCustom.getSellPoint());
            document.addField("item_price",tbItemSearchCustom.getPrice());
            document.addField("item_image",tbItemSearchCustom.getImage());
            try {
                solrServer.add(document);

            }catch (SolrServerException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        try {
            solrServer.commit();
        }catch (SolrServerException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public TbSearchItemResult search(String keyword, int page, int rows) {
        SolrQuery solrQuery = new SolrQuery();

        solrQuery.setQuery(keyword);
        if(page<=0){
            page = 1;
        }
        solrQuery.setStart((page-1)*rows);
        solrQuery.setRows(rows);
        solrQuery.set("df","item_keywords");

        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePost("</em>");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");

        //调用dao执行查询
        TbSearchItemResult searchResult = searchItemDao.search(solrQuery);
        //计算总页数
        long recordCount = searchResult.getRecordCount();
        int totalPage = (int) (recordCount + rows - 1/ rows);

        //添加到返回结果
        searchResult.setTotalPages(totalPage);
        return searchResult;
    }
}
