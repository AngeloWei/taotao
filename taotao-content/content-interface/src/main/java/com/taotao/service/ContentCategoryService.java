package com.taotao.service;

import com.taotao.utils.EasyUiTreeNode;
import com.taotao.utils.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    List<EasyUiTreeNode> getContentCategory(Long parentId);
    TaotaoResult addContentCategory(Long patentId,String name);
    TaotaoResult deleteContentCategory(Long id);
    TaotaoResult updateName(Long id,String name);
}
