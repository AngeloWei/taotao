package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import com.taotao.utils.EasyUiResult;
import com.taotao.utils.IdUtils;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    /**
     * @param item
     * @param desc
     * @return
     */
    @Override
    public TaotaoResult addItam(TbItem item, String desc) {
        //添加id
        Long id = IdUtils.getItemId();
        item.setId(id);
        //添加状态，1为正常 2 下架   3 删除
        item.setStatus((byte) 1);
        //创建时间和更新时间
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        //插入item对象
        tbItemMapper.insert(item);
        //插入tbItemDesc
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(id);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemDesc(desc);
        tbItemDescMapper.insert(tbItemDesc);
        return TaotaoResult.ok();

    }

    @Override
    public TaotaoResult updateItem(TbItem item, String desc) {
        //先更新item 数据
        item.setCreated(null);
        item.setStatus(null);
        int i1 = tbItemMapper.updateByPrimaryKeySelective(item);

        //更新描述
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(item.getId());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(null);
        int i2 = tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
        if (i1 == 1 && i2 == 1)
            return TaotaoResult.ok();
        else
            return new TaotaoResult(3, "更新数据出错", null);
    }

    /**
     * @param ids
     * @return
     */
    @Override
    public TaotaoResult deleteItems(List<Long> ids) {
        //创建itemexample 设置查询条件
        TbItemExample tbItemExample = new TbItemExample();
        tbItemExample.createCriteria().andIdIn(ids);
        int i = tbItemMapper.deleteByExample(tbItemExample);
        return TaotaoResult.ok();

    }

    @Override
    public TaotaoResult changeStatus(List<Long> ids,short status) {
        tbItemMapper.changeStatus(ids,status);
        return TaotaoResult.ok();
    }


    @Override
    public TbItem getItemById(long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public EasyUiResult getItemList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        //查询
        List<TbItem> tbItems = tbItemMapper.selectByExample(new TbItemExample());
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItems);
        EasyUiResult result = new EasyUiResult(pageInfo.getTotal(), tbItems);
        return result;
    }

}
