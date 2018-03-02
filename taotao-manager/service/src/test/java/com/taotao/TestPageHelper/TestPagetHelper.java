package com.taotao.TestPageHelper;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext-dao.xml")
public class TestPagetHelper {
    @Resource
    private  TbItemMapper tbItemMapper;
    @Test
    public void getPage() {
            //使用pageHelper来实现分页
        PageHelper.startPage(1, 30);
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> tbItems = tbItemMapper.selectByExample(tbItemExample);
        //将查询结果放入分页类中
        PageInfo<TbItem> tbItemPageInfo = new PageInfo<>(tbItems);
    }
}
