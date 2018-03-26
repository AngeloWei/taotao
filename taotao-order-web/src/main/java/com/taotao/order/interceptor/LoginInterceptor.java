package com.taotao.order.interceptor;

import com.taotao.account.service.AccountService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor{

    @Value("${TT_Token}")
    private String TT_Token;
    @Value("${SSO_LOGIN_URL}")
    private String SSO_LOGIN_URL;

    @Autowired
    private AccountService accountService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
       //提交订单前，需要判断用户是否登录
        String tt_token = CookieUtils.getCookieValue(httpServletRequest, "TT_Token");
        if (StringUtils.isBlank(tt_token)) {
            String url = httpServletRequest.getRequestURL().toString();
            httpServletResponse.sendRedirect(SSO_LOGIN_URL + "?redirectUrl=" + url);
            return false;
        }
        //查询token
        TaotaoResult result = accountService.getInfoByToken(tt_token);
        if (result.getStatus() != 200) {
            String url = httpServletRequest.getRequestURL().toString();
            httpServletResponse.sendRedirect(SSO_LOGIN_URL + "?redirectUrl=" + url);
            return false;
        }
        //存在，放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
