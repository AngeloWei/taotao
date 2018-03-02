package com.taotao.utils;

import java.io.Serializable;
import java.util.List;

public class EasyUiResult implements Serializable{

    private Integer total;  //数据总数
    private List rows;      //数据列表

    public EasyUiResult(Integer total,List rows) {
        this.rows=rows;
        this.total=total;
    }
    public EasyUiResult(Long total,List rows) {
        this.total=total.intValue();
        this.rows=rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
