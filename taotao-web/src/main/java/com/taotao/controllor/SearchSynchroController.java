package com.taotao.controllor;

import com.taotao.service.SearchService;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchSynchroController {
   /* @Autowired
    private SearchService searchService;
    @RequestMapping("/solr/indexSynchro")
    @ResponseBody
    public TaotaoResult SearchIndexSynchro() throws  Exception{
        return searchService.getItemList();
    }*/
}
