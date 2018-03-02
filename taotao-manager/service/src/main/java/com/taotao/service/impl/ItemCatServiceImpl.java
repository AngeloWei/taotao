package com.taotao.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import com.taotao.utils.EasyUiTreeNode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Resource
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public List<EasyUiTreeNode> getItemCatList(long parentId) {
        //根据父节点，查询子节点
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        tbItemCatExample.createCriteria().andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(tbItemCatExample);
        //转换层EasyUiTreeNode列表
        List<EasyUiTreeNode> list = new ArrayList<EasyUiTreeNode>();
        for (TbItemCat tbItemcat: tbItemCats
             ) {
            EasyUiTreeNode easyUiTreeNode = new EasyUiTreeNode();
            easyUiTreeNode.setId(tbItemcat.getId());
            easyUiTreeNode.setText(tbItemcat.getName());
            //设置节点状态，如果没有子节点，状态为open
            easyUiTreeNode.setState(tbItemcat.getIsParent()?"closed":"open");
            list.add(easyUiTreeNode);
        }
        return list;
    }
}
