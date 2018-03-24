package com.taotao.account.service;

import com.taotao.pojo.TbUser;
import com.taotao.utils.TaotaoResult;

import java.lang.reflect.Type;

public interface AccountService {

    //校验参数
    TaotaoResult checkData(String parame ,int type);

    //登录
    TaotaoResult userLogin(String account, String passwd);

    //注册
    TaotaoResult userRegister(TbUser tbUser);

    //通过token查询信息
    TaotaoResult getInfoByToken(String token);

    //退出登录
    TaotaoResult userLogout(String token, String userId);
}
