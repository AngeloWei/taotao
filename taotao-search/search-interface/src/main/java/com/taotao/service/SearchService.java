package com.taotao.service;

import com.taotao.utils.SearchItem;
import com.taotao.utils.SearchResult;
import com.taotao.utils.TaotaoResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface SearchService {
    TaotaoResult getItemList() throws IOException, SolrServerException;

    SearchResult resultQuery(String queryString ,int rows,int page);

}
