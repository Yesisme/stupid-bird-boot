package com.epsoft.demo.mq;

import com.alibaba.fastjson.JSON;
import com.epsoft.demo.SpringBootDemoApplication;
import com.epsoft.demo.bean.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
@Slf4j
public class ListnerTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    //普通类型的testSimpleMessageListenerContainer
    @Test
    public void testSimpleMessageListenerContainer(){
        MessageProperties properties = new MessageProperties();
        properties.getHeaders().put("des","测试SimpleMessageListenerContainer信息");
        properties.setContentType("text/plain");

        Message message = new Message("mq消息".getBytes(),properties);
        rabbitTemplate.send("test_spring01_topic_exchange","spring.abc",message);

        rabbitTemplate.convertAndSend("test_spring01_topic_exchange","spring.rabbitmq","hello topic_euque message send");
        rabbitTemplate.convertAndSend("test_spring02_direct_exchange","rabbit.mq","hello direct_queue message send");
    }

    //自定义适配器测试
    @Test
    public void testSendMessage() {
        //创建消息
        MessageProperties properties = new MessageProperties();
        properties.getHeaders().put("des","properties描述信息");
        Message message = new Message("hello rabbitmq properties".getBytes(),properties);

        //还可以转换
        rabbitTemplate.convertAndSend("test_spring01_topic_exchange", "spring.abc", message, new MessagePostProcessor() {

            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                log.info("添加额外信息描述{}","----------------------");
                message.getMessageProperties().getHeaders().put("type","我是convertAndSend类型的");
                return message;
            }
        });

        rabbitTemplate.send("test_spring01_topic_exchange", "spring.amqp",message);
    }


    //text/plain类型需要经过方法转换
    @Test
    public void testMessageListenerAdapterForTexe(){
        MessageProperties properties = new MessageProperties();
        properties.getHeaders().put("des","测试MessageListenerAdapterForTexe信息");
        properties.setContentType("text/plain");

        Message message = new Message("mq消息".getBytes(),properties);
        rabbitTemplate.send("test_spring01_topic_exchange","spring.abc",message);
    }

    @Test
    public void testQueueOrTagToMethodName(){
        MessageProperties properties = new MessageProperties();
        properties.getHeaders().put("desc","测试队列绑定方法");
        properties.setContentType("text/plain");
        Message message = new Message("hello queueOrTagToMethodName".getBytes(),properties);
        rabbitTemplate.send("test_spring01_topic_exchange","spring.qwe",message);
        rabbitTemplate.send("test_spring02_direct_exchange","rabbit.mq",message);
    }

    @Test
    public void testAdepatJson(){
        User user = new User();
        user.setId(12L);
        user.setUserName("章韩遂");
        user.setSex("男");
        user.setPhone("13546466");
        String json = JSON.toJSONString(user);

        MessageProperties properties = new MessageProperties();
        properties.setContentType("application/json");
        Message message = new Message(json.getBytes(),properties);
        rabbitTemplate.send("test_spring01_topic_exchange","spring.org",message);
    }

    @Test
    public void testAdepatJaveObject() throws Exception {
        User user = new User();
        user.setId(14L);
        user.setUserName("王牌飞行员");
        user.setSex("男");
        user.setPhone("6666");
        String json = JSON.toJSONString(user);

        MessageProperties properties = new MessageProperties();
        properties.setContentType("application/json");
        properties.getHeaders().put("__TypeId__","com.epsoft.demo.bean.entity.User");
        Message message = new Message(json.getBytes(),properties);
        rabbitTemplate.send("test_spring01_topic_exchange","spring.framework",message);
    }
}
