package com.taotao.search.controller;

import com.taotao.service.SearchService;
import com.taotao.utils.SearchResult;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;


    /**
     *
     * @param queryString
     * @param page
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/search")
    private String searchByKey(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page, Model model)throws  Exception {
        int rows =30;
        //进行转码
        queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
        SearchResult result = searchService.resultQuery(queryString, rows, page);
        //把查询结果添加到model
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", result.getPageCount());
        model.addAttribute("itemlist", result.getSearchItemList());
        model.addAttribute("page", page);
        return "search";
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/solr/indexSynchro")
    @ResponseBody
    public TaotaoResult SearchIndexSynchro() throws  Exception{
        return searchService.getItemList();
    }
}
