package com.taotao.service;


import com.taotao.utils.EasyUiTreeNode;

import java.util.List;

public interface ItemCatService {
    List<EasyUiTreeNode> getItemCatList(long parentId);
}
