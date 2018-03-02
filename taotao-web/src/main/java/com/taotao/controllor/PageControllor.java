package com.taotao.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* 页面跳转
*
* */
@Controller
public class PageControllor {

    @RequestMapping("/")
    public String getIndex(){
        return "index";
    }
    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page) {
        return page;
    }

}
