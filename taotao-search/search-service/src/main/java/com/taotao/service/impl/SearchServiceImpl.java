package com.taotao.service.impl;


import com.taotao.service.SearchService;
import com.taotao.service.dao.SearchDao;
import com.taotao.service.mapper.SearchItemMapper;
import com.taotao.utils.SearchItem;
import com.taotao.utils.SearchResult;
import com.taotao.utils.TaotaoResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchItemMapper searchItemMpper;
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private SearchDao searchDao;

    @Override
    public TaotaoResult getItemList() {
        //1.查询数据库结果
        List<SearchItem> list = searchItemMpper.getSearchList();
        //2.将结果插入到搜索服务器
        //3.为每个商品创建document对象
        for (SearchItem item :list
             ) {
            //为文档添加域
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", item.getId());
            document.addField("item_title", item.getTitle());
            document.addField("item_desc", item.getItemDesc());
            document.addField("item_sell_point", item.getSellPoint());
            document.addField("item_category_name", item.getCategoryName());
            document.addField("item_price", item.getPrice());
            document.addField("item_image", item.getImage());
            try {
                solrServer.add(document);
            } catch (SolrServerException e) {
                e.printStackTrace();
                return TaotaoResult.bad();
            } catch (IOException e) {
                e.printStackTrace();
                return TaotaoResult.bad();
            }
        }
        try {
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }

    @Override
    public SearchResult resultQuery(String queryString, int rows, int page) {
        //1.创建solrquery对象
        SolrQuery query = new SolrQuery();
        //2.设置查询条件
        query.setQuery(queryString);
        //3.设置分页条件
        query.setStart((page - 1) * rows);
        query.setRows(rows);
        //4.设置默认域
        query.set("df", "item_keywords");
        //5.设置高亮
        query.setHighlight(true);
        query.addHighlightField("item_sell_point");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        //执行查询
        SearchResult result=null;
        try {
            result = searchDao.SearchByQuery(query);
            int recordCount = result.getRecordCount();
            int pageCount = recordCount/rows;
            if (recordCount % rows!=0) {
                pageCount++;
            }
            result.setPageCount(pageCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }



}
