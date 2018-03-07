package com.taotao.service;

import com.taotao.pojo.TbContent;
import com.taotao.utils.EasyUiResult;
import com.taotao.utils.TaotaoResult;

import java.util.List;

public interface ContentService {
    //根据categoryId进行查询
    EasyUiResult getContentList(int page, int rows,long categoryId);

    List<TbContent> getContentByCategoryId(long id);

    TaotaoResult updateConten(TbContent content);

    TaotaoResult deleteConten(long id);

    TaotaoResult addContent(TbContent content);
}
