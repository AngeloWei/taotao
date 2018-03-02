package com.taotao.controllor;

import com.taotao.service.ItemCatService;
import com.taotao.utils.EasyUiTreeNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ItamCatControllor {
    @Resource
    private ItemCatService itemCatService;
    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUiTreeNode> getItamCatList(@RequestParam(value = "id" ,defaultValue = "0") Long parenId) {
        return itemCatService.getItemCatList(parenId);
    }
}
