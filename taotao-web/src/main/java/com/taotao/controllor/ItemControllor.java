package com.taotao.controllor;

import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import com.taotao.utils.EasyUiResult;
import com.taotao.utils.TaotaoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
public class ItemControllor {
    @Resource
    private ItemService itemService;

    /**
     * @param itemId
     * @return
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId) {
        return itemService.getItemById(itemId);
    }

    /**
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUiResult getItemList(Integer page, Integer rows) {
        return itemService.getItemList(page, rows);
    }

    /**
     * @param tbItem
     * @param desc
     * @return
     */
    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult saveItem(TbItem tbItem, String desc) {
        TaotaoResult taotaoResult = itemService.addItam(tbItem, desc);
        return taotaoResult;
    }

    @RequestMapping("/rest/page/item-edit")
    public String toEditPage() {
        return "item-edit";
    }

    /**
     * 更新商品信息
     *
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping("/rest/item/update")
    @ResponseBody
    public TaotaoResult updateItem(TbItem item, String desc) {
        TaotaoResult taotaoResult = itemService.updateItem(item, desc);
        return taotaoResult;
    }

    /*
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult deleteItems(ArrayList<Long> ids) {
        return itemService.deleteItems(ids);
    }*/
    @RequestMapping("/rest/item/{change}")
    @ResponseBody
    public TaotaoResult changeItemStauts(@RequestParam("ids") ArrayList<Long> ids, @PathVariable String change) {
        //下架
        if ("instock".equals(change))
            return itemService.changeStatus(ids, (short) 2);
        else if ("reshelf".equals(change))
            return itemService.changeStatus(ids, (short) 1);
        else if ("delete".equals(change))
            return itemService.changeStatus(ids, (short) 3);
        return TaotaoResult.bad();
    }


}
