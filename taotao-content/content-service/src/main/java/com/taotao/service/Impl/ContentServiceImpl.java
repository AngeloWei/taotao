package com.taotao.service.Impl;

import com.alibaba.druid.util.DaemonThreadFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import com.taotao.utils.EasyUiResult;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.ExportException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    TbContentMapper tbContentMapper;
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
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(id);
        List<TbContent> tbContents = tbContentMapper.selectByExample(example);
        return tbContents ;
    }

    @Override
    public TaotaoResult updateConten(TbContent content) {
        content.setUpdated(new Date());

        tbContentMapper.updateByPrimaryKeySelective(content);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteConten(long id) {
        tbContentMapper.deleteByPrimaryKey((long) id);
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
