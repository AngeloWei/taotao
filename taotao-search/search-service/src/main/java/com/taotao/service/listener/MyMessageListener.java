package com.taotao.service.listener;


import com.taotao.service.SearchService;
import com.taotao.utils.SearchItem;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {
    @Autowired
    private SearchService searchService;
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        //根据id查询信息
        try {
            long id = Long.parseLong(textMessage.getText());
            Thread.sleep(2000);
            searchService.addDocument(id);
            System.out.println("索引库已经同步");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
