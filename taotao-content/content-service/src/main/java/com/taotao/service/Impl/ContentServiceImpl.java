package com.taotao.service.Impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import com.taotao.utils.EasyUiResult;
import com.taotao.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${index_content}")
    private String index_content;
    @Override
    public EasyUiResult getContentList(int page, int rows,long categoryId) {
        //使用分页查询contentList
        PageHelper.startPage(page, rows);
        //执行查询
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = tbContentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo = new PageInfo<>(tbContents);
        return new EasyUiResult(pageInfo.getTotal(),tbContents);
    }

    @Override
    public List<TbContent> getContentByCategoryId(long id) {
        //查询缓存
        try {
            String json = jedisClient.hget(index_content, id + "");
            if (StringUtils.isNotBlank(json)) {
                List<TbContent> list = JSON.parseArray(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //没有则在mysql 查询
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(id);
        List<TbContent> tbContents = tbContentMapper.selectByExample(example);
        //将查询结果插入redis
        try {
            jedisClient.hset(index_content,id+"",JSON.toJSON(tbContents).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents ;
    }

    @Override
    public TaotaoResult updateConten(TbContent content) {
        content.setUpdated(new Date());

        tbContentMapper.updateByPrimaryKeySelective(content);
        try {
            jedisClient.hdel(index_content, content.getCategoryId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteConten(long id) {
        tbContentMapper.deleteByPrimaryKey((long) id);
        try {
            jedisClient.hdel(index_content,id+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult addContent(TbContent content) {
        Date date = new Date();
        content.setCreated(date);
        content.setUpdated(date);
        tbContentMapper.insertSelective(content);

        return TaotaoResult.ok(content);
    }
}
