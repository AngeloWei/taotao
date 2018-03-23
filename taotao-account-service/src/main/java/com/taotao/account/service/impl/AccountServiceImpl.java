package com.taotao.account.service.impl;

import com.taotao.account.service.AccountService;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountServiceImpl implements AccountService{

    @Autowired
    private TbUserMapper tbUserMapper;



    @Override
    public TaotaoResult checkData(String parame, int type) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();

        if (1 == type) {
            criteria.andUsernameEqualTo(parame);
        } else if (2 == type) {
            criteria.andPhoneEqualTo(parame);
        } else if (3 == type) {
            criteria.andEmailEqualTo(parame);
        } else {
            return TaotaoResult.bad();
        }
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        if (tbUsers == null || tbUsers.size() == 0) {
            return TaotaoResult.ok(true);
        }
        return TaotaoResult.ok(false);
    }

    @Override
    public TaotaoResult userLogin(String account, String passwd) {
        

        return null;
    }

    @Override
    public TaotaoResult userRegister(TbUser tbUser) {
        return null;
    }

    @Override
    public TaotaoResult getInfoByToken(String token) {
        return null;
    }

    @Override
    public TaotaoResult userLogout(String token, String userId) {
        return null;
    }
}
