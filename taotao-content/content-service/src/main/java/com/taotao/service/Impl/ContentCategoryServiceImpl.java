package com.taotao.service.Impl;

import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import com.taotao.utils.EasyUiTreeNode;
import com.taotao.utils.IdUtils;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EasyUiTreeNode> getContentCategory(Long parentId) {
        //1.查询类目分类信息
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<TbContentCategory> result = tbContentCategoryMapper.selectByExample(example);
        List<EasyUiTreeNode> list = new ArrayList<>();
        for (TbContentCategory category : result
             ) {
            EasyUiTreeNode node = new EasyUiTreeNode();
            node.setId(category.getId());
            node.setText(category.getName());
            node.setState(category.getIsParent()?"closed":"close");
            list.add(node);
        }
        return list;
    }

    @Override
    public TaotaoResult addContentCategory(Long patentId, String name) {
        //补全category信息
        TbContentCategory category = new TbContentCategory();
        Date date = new Date();
        category.setName(name);
        category.setCreated(date);
        category.setUpdated(date);
        category.setIsParent(false);
        //设置排序号
        category.setSortOrder(1);
        return null;
    }

    @Override
    public TaotaoResult deleteContentCategory(Long id) {
        return null;
    }

    @Override
    public TaotaoResult updateName(Long id, String name) {
        return null;
    }
}
