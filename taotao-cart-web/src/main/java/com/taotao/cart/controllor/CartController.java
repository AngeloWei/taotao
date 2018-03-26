package com.taotao.cart.controllor;

import com.alibaba.fastjson.JSON;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class CartController {

    @Value("${TT_COOKIE}")
    private String TT_COOKIE;

    @Autowired
    private ItemService itemService;

    /**
     *购物车添加商品
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/cart/add/{itemId}")
    @ResponseBody
    public String addItemToCookie(@PathVariable Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {

        List<TbItem> itemList = getItemList(request);
        //商品在列表中，直接修改num数量
        boolean isExist = true;
        if (itemList.size() == 0) {
            isExist = false;
        }
        if (isExist) {
            for (TbItem item : itemList
                    ) {
                if (itemId == item.getId()) {
                    int temNum = item.getNum() + num;
                    item.setNum(temNum);
                    break;
                }
            }
        }
        if (!isExist) {
            //添加商品
            TbItem item = itemService.getItemById(itemId);
            String[] split = item.getImage().split(",");
            if (split.length != 0) {
                item.setImage(split[0]);
            }
            item.setNum(num);
            itemList.add(item);
        }
        //把itemList写入cookie中
        String json = JSON.toJSONString(itemList);
        CookieUtils.setCookie(request, response, TT_COOKIE, json, true);
        return "cartSuccess";
    }

    /**
     * 购物车更新商品数量
     */
    @RequestMapping("/cart/update/{itemId}/{num}")
    @ResponseBody
    public TaotaoResult updateItemNum(@PathVariable Long itemId,@PathVariable Integer num, HttpServletResponse response, HttpServletRequest request) {
        List<TbItem> itemList = getItemList(request);
        if (itemList.size() == 0) {
            return new TaotaoResult(3, "无数据，无法更新", null);
        }
        boolean isExist=false;
        for (TbItem item : itemList
                ) {
            if (itemId == item.getId()) {
                item.setNum(num);
                CookieUtils.setCookie(request, response, TT_COOKIE, JSON.toJSONString(itemList), true);
                isExist=true;
                break;
            }
        }
        if (!isExist) {
            return new TaotaoResult(3, "购物车无该商品，请添加", null);
        }
        return TaotaoResult.ok();
    }

    /**
     * 获取购物车列表
     */
    @RequestMapping("/cart/cartList")
    @ResponseBody
    public String getCartList(HttpServletRequest request, HttpServletResponse response, Model model) {

        List<TbItem> itemList = getItemList(request);
        model.addAttribute("itemList", itemList);
        return "cart";
    }

    @RequestMapping("/cart/del/{cartId}")
    @ResponseBody
    public String delCart(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "false") boolean clean, @PathVariable Long itemId) {
        if (clean) {
            CookieUtils.setCookie(request, response, TT_COOKIE, null);
            return "cart";
        }
        List<TbItem> itemList = getItemList(request);
        Iterator<TbItem> iterator = itemList.iterator();
        while(iterator.hasNext()) {
            if (iterator.next().getId() == itemId) {
                iterator.remove();
                break;
            }
        }
        CookieUtils.setCookie(request, response, TT_COOKIE, JSON.toJSONString(itemList));
        return "cart";
    }
    /**
     * 从Cookie中获取商品列表
     *
     * @return
     */
    public List<TbItem> getItemList(HttpServletRequest request) {

        String value = CookieUtils.getCookieValue(request, TT_COOKIE);

        if (StringUtils.isNotBlank(value)) {
            List<TbItem> tbItems = JSON.parseArray(value, TbItem.class);
            return tbItems;
        }
        return new ArrayList<>();
    }


}
