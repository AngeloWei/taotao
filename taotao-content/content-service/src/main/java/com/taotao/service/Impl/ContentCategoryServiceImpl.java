package com.taotao.service.Impl;

import com.sun.webkit.dom.EntityImpl;
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
    public TaotaoResult addContentCategory(Long parentId, String name) {
        //补全category信息
        TbContentCategory category = new TbContentCategory();
        Date date = new Date();
        category.setName(name);
        category.setCreated(date);
        category.setUpdated(date);
        category.setIsParent(false);
        //设置排序号
        category.setSortOrder(1);
        //1为正常，2为删除
        category.setStatus(1);
        category.setParentId(parentId);
        tbContentCategoryMapper.insertSelective(category);
        //更新父类状态
        TbContentCategory parent = new TbContentCategory();
        parent.setId(parentId);
        parent.setIsParent(true);
        //修改父节点状态为isParents
        tbContentCategoryMapper.updateByPrimaryKeySelective(parent);
        return TaotaoResult.ok(category);
    }

    @Override
    public TaotaoResult deleteContentCategory(Long id) {
        //调用删除节点函数
        TbContentCategory category = tbContentCategoryMapper.selectByPrimaryKey(id);
        deleteByMeth(id);
        //如果parentid 没有子节点，则把parentid 设置为子节点
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(category.getParentId());
        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(example);
        if (tbContentCategories.isEmpty()) {
            TbContentCategory entity = new TbContentCategory();
            entity.setId(category.getParentId());
            entity.setIsParent(false);
            tbContentCategoryMapper.updateByPrimaryKeySelective(entity);
        }
        return  TaotaoResult.ok();
    }

    public void   deleteByMeth(Long id) {
        //查询该节点下的子节点
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(id);
        List<TbContentCategory> categories = tbContentCategoryMapper.selectByExample(example);
        //节点下的子节点数为0，删除该节点
        if (!categories.isEmpty()) {
            for (TbContentCategory entity:categories
                    ) {
                deleteByMeth(entity.getId());
            }
        }
        tbContentCategoryMapper.deleteByPrimaryKey(id);
    }
    @Override
    public TaotaoResult updateName(Long id, String name) {
        TbContentCategory entity = new TbContentCategory();
        entity.setId(id);
        entity.setName(name);
        tbContentCategoryMapper.updateByPrimaryKeySelective(entity);
        return TaotaoResult.ok();
    }
}
