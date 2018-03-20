package com.taotao.service.dao;


import com.taotao.utils.SearchItem;
import com.taotao.utils.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询索引库结果
 */
@Repository
public class SearchDao {
    @Autowired
    private SolrClient solrServer;

    public SearchResult SearchByQuery(SolrQuery query)throws  Exception {
        //根据query查询索引库
        QueryResponse response = solrServer.query(query);
        //获取查询结果
        SolrDocumentList documentList = response.getResults();
        //获取高亮结果
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        //将结果转换为List<SearchItem>
        List<SearchItem> list = new ArrayList<>();
        for (SolrDocument document :documentList
                ) {
                SearchItem item = new SearchItem();
                item.setId(Long.parseLong(document.get("id").toString()));
                item.setCategoryName((String) document.get("item_category_name"));
                item.setImage((String) document.get("item_image"));
                item.setPrice(Long.parseLong(document.get("item_price").toString()));
                //获取关键字高亮
                List<String> stringList = highlighting.get(document.get("id")).get("item_sell_point");
            if (stringList != null && stringList.size() > 0) {
                item.setSellPoint(stringList.get(0));
            } else {
                item.setSellPoint((String) document.get("item_sell_point"));
            }
                item.setTitle((String) document.get("item_title"));
                item.setItemDesc((String) document.get("item_desc"));
            list.add(item);
        }
        SearchResult result =new SearchResult();
        //将结果封装到SearchResult
        result.setSearchItemList(list);
        result.setRecordCount((int) documentList.getNumFound());
        return  result;
    }
}
