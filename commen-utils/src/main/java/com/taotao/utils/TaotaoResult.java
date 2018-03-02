package com.taotao.utils;

import java.io.Serializable;

public class TaotaoResult implements Serializable{
    private Integer status;
    private String message;
    private Object data;

    public TaotaoResult(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public TaotaoResult(Object data) {
        this.status = 200;
        this.message = "Operation Success";
        this.data = data;
    }

    public static TaotaoResult ok(Object data) {
        return new TaotaoResult(data);
    }
    public static TaotaoResult ok() {
        return new TaotaoResult(null);
    }
    public static TaotaoResult bad() {
        return new TaotaoResult(3, "Operation fail", null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
