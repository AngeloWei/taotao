package com.taotao.item.listener;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ItemAndMessageListener implements MessageListener {
    @Autowired
    private ItemService itemService;
    //注入freemarkerConfig
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;
    @Value("${HTML_OUT_PATH}")
    private String  HTML_OUT_PATH;

    /**
     * 添加商品获取消息，生成商品详情静态页面，topicDestination：item-change-topic
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        //从消息中取itemId
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            Long itemId = Long.parseLong(text);
            //准备数据，获取item和itemDesc对象
            TbItem itemById = itemService.getItemById(itemId);
            Item item = new Item(itemById);
            TbItemDesc itemDesc = itemService.getItemDescById(itemId);

            //获取configuration对象
            Configuration configuration = freeMarkerConfig.getConfiguration();
            //加载模板，获取template对象
            Template template = configuration.getTemplate("item.ftl");

            Map data = new HashMap();
            data.put("item", item);
            data.put("itemDesc", itemDesc);
            Writer out = new FileWriter(new File(HTML_OUT_PATH+itemId+".html"));
            template.process(data, out);

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
