package com.taotao.order.controllor;

import com.alibaba.fastjson.JSON;
import com.taotao.pojo.TbItem;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Value("TT_COOKIE")
    private String TT_COOKIE;

    /**
     *
     * 获取购物车cookie信息，跳转到订单页面
     * @param request
     * @return
     */
    @RequestMapping("/order/order-cart")
    @ResponseBody
    public String showORderPre(HttpServletRequest request) {
        List<TbItem> tbItems = itemList(request);
        request.setAttribute("cartList", tbItems);
        return "order-cart";
    }

    public TaotaoResult CreateOrder(HttpServletRequest request) {
        return TaotaoResult.ok();
    }


    /**
     *  查询cookie中的物品信息
     * @param request
     * @return
     */
    public List<TbItem> itemList(HttpServletRequest request) {

        String cookieValue = CookieUtils.getCookieValue(request, TT_COOKIE,true);
        if (cookieValue.length() != 0) {
            return JSON.parseArray(cookieValue,TbItem.class
            );
        }
        return new ArrayList<>();
    }
}
