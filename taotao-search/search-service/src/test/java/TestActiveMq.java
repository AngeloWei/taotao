import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext-activeMq.xml")
public class TestActiveMq {

    @Autowired
    private Queue queueDestination;
    @Autowired
    private JmsTemplate jmsTemplate;


    @Test
    public void testQuequeMq() throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.2.128:61616");

        Connection connection = connectionFactory.createConnection();

        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("test-queue");

        MessageProducer producer = session.createProducer(queue);

        TextMessage message = session.createTextMessage("这是一个queue测试");

        producer.send(message);

        producer.close();

        session.close();

        connection.close();

    }
    @Test
    public void testQueueConsumer() throws Exception {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.2.128:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");

        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
            System.in.read();
            consumer.close();
            session.close();
            connection.close();
    }

    @Test
    public void testQueueSpring() throws Exception {

        jmsTemplate.send(queueDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                TextMessage textMessage = session.createTextMessage("Spring整合activeMq");

                return textMessage;
            }
        });
    }
}
