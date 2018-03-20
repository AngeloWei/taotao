package com.taotao.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.taotao.utils.SearchItem;
import com.taotao.utils.SearchResult;
import com.taotao.utils.TaotaoResult;

public interface SearchService {

    TaotaoResult getItemList() ;

    SearchResult resultQuery(String queryString ,int rows,int page);

    TaotaoResult addDocument(long id);

}
