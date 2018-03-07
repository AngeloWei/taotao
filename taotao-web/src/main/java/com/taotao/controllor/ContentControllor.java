package com.taotao.controllor;

import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import com.taotao.utils.EasyUiResult;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class ContentControllor {
    @Autowired
    private ContentService contentService;
    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUiResult getContentList(Integer page, Integer rows,long categoryId) {
      return   contentService.getContentList(page, rows,categoryId);
    }
    @RequestMapping("/content/save")
    @ResponseBody
    public TaotaoResult addContent(TbContent entity) {
        return contentService.addContent(entity);
    }

    @RequestMapping("/rest/content/edit")
    @ResponseBody
    public TaotaoResult updateContent(TbContent entity) {
        return contentService.updateConten(entity);
    }
    @RequestMapping("/content/delete")
    @ResponseBody
    public TaotaoResult deleteContent(Long ids) {
        return contentService.deleteConten(ids);
    }
}
