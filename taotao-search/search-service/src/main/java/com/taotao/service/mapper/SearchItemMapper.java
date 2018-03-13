package com.taotao.service.mapper;

import com.taotao.utils.SearchItem;

import java.util.List;

public interface SearchItemMapper {

    //同步数据库中的数据到搜索服务器
    List<SearchItem> getSearchList();
}
