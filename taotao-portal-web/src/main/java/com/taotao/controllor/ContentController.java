package com.taotao.controllor;

import com.alibaba.fastjson.JSON;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import com.taotao.utils.An1Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;
    @Value("${AD1_CID}")
   private String AD1_CID;
    @Value("${AD1_HEIGHT}")
    private String AD1_HEIGHT;
    @Value("${AD1_HEIGHT_B}")
    private String AD1_HEIGHT_B;
    @Value("${AD1_WIDTH}")
    private String AD1_WIDTH;
    @Value("${AD1_WIDTH_B}")
    private String AD1_WIDTH_B;
    @RequestMapping("/index")
    public String getContenList(Model model) {
        List<TbContent> result = contentService.getContentByCategoryId(new Long(AD1_CID));
        List<An1Node> list = new ArrayList<>();
        for (TbContent content: result
             ) {
            An1Node node = new An1Node();
            node.setAlt(content.getTitle());
            node.setHeight(AD1_HEIGHT);
            node.setHeightB(AD1_HEIGHT_B);
            node.setWidth(AD1_WIDTH);
            node.setWidthB(AD1_WIDTH_B);
            node.setSrc(content.getPic());
            node.setSrcB(content.getPic2());
            node.setHref(content.getUrl());
            list.add(node);
        }
        model.addAttribute("ad1", JSON.toJSON(list));
        return "index";
    }

}
