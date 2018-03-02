package com.taotao.controllor;

import com.taotao.service.ContentCategoryService;
import com.taotao.utils.EasyUiTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoryControllor {

    @Autowired
    private ContentCategoryService contentCategoryService;
    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUiTreeNode> getCategoryList(@RequestParam (value = "id",defaultValue = "0") Long parentId) {
        List<EasyUiTreeNode> list = contentCategoryService.getContentCategory(parentId);
        return list;
    }

}
