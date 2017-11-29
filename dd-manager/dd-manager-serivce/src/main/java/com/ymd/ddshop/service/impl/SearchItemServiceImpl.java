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
    public TbSearchItemResult search(String keyword, int page, int rows)  {
        TbSearchItemResult searchResult = new TbSearchItemResult();
            //创建一个SolrQuery对象
            SolrQuery query = new SolrQuery();
            //设置查询条件
            query.setQuery(keyword);
            //设置分页条件
            if (page <=0 ) page = 1;
            query.setStart((page - 1) * rows);
            query.setRows(rows);
            //设置默认搜索域
            query.set("df", "item_keywords");
            //开启高亮显示
            query.setHighlight(true);
            query.addHighlightField("item_title");
            query.setHighlightSimplePre("<em style=\"color:red\">");
            query.setHighlightSimplePost("</em>");
            //调用dao执行查询
            try {
                searchResult  = searchItemDao.search(query);
            }catch (Exception e){
                e.printStackTrace();
            }

            //计算总页数
            long recordCount = searchResult.getRecordCount();
            int totalPage = (int) (recordCount / rows);
            if (recordCount % rows > 0){
                totalPage ++;
            }
            //添加到返回结果
            searchResult.setTotalPages(totalPage);
            //返回结果
            return searchResult;
    }
}
