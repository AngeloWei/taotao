package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.utils.EasyUiResult;
import com.taotao.utils.TaotaoResult;

import java.util.List;

public interface ItemService {
    TbItem getItemById(long itemId);

    EasyUiResult getItemList(int page, int rows);

    TaotaoResult addItam(TbItem item, String desc);

    TaotaoResult updateItem(TbItem item, String desc);

    TaotaoResult deleteItems(List<Long> ids);

    TaotaoResult changeStatus(List<Long> ids,short status);

    TbItemDesc getItemDescById(long itemId);

}
