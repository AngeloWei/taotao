package com.taotao.utils;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable{
    //查询的结果列表
    private List<SearchItem> searchItemList;
    //总记录数
    private int recordCount;
    //分页数
    private int pageCount;

    public List<SearchItem> getSearchItemList() {
        return searchItemList;
    }

    public void setSearchItemList(List<SearchItem> searchItemList) {
        this.searchItemList = searchItemList;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
