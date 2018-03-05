package com.taotao.controllor;

import com.taotao.service.ContentCategoryService;
import com.taotao.utils.EasyUiTreeNode;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;
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
    @RequestMapping("/content/category/create")
    @ResponseBody
    public TaotaoResult addCategory(Long parentId, String name) {
        return contentCategoryService.addContentCategory(parentId, name);
    }
    @RequestMapping("/content/category/update")
    @ResponseBody
    public TaotaoResult updateCategory(Long id, String name) {
        return contentCategoryService.updateName(id, name);
    }
    @RequestMapping("/content/category/delete")
    @ResponseBody
    public TaotaoResult deleteCategory(Long id) {
        return contentCategoryService.deleteContentCategory(id);
    }

}
