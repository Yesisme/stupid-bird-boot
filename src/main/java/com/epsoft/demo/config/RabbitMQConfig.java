package com.epsoft.demo.config;


import com.epsoft.demo.mq.rabbit.ImageMessageConverter;
import com.epsoft.demo.mq.rabbit.MessageDelegate;
import com.epsoft.demo.mq.rabbit.PDFMessageConverter;
import com.epsoft.demo.mq.rabbit.TextMessageConver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class RabbitMQConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("192.168.96.128:5672");
        connectionFactory.setUsername("lym");
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("/vhost_lym");
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     * HeadersExchange ：通过添加属性key-value匹配
     * DirectExchange:按照routingkey分发到指定队列
     * TopicExchange:多关键字匹配
     */
    @Bean
    public TopicExchange exchange001() {
        return new TopicExchange("test_spring01_topic_exchange", false, false);
    }

    @Bean
    public Queue queue001() {
        return new Queue("test_spring01_topic_queue", false); //队列持久
    }

    @Bean
    public Binding binding001() {
        return BindingBuilder.bind(queue001()).to(exchange001()).with("spring.*");
    }

    @Bean
    public DirectExchange exchange002() {
        return new DirectExchange("test_spring02_direct_exchange", false, false);

    }

    @Bean
    public Queue queue002() {
        return new Queue("test_spring02_direct_queue", false);
    }

    @Bean
    public Binding bingding002() {
        return BindingBuilder.bind(queue002()).to(exchange002()).with("rabbit.mq");
    }


    //SimpleMessageListenerContainer 消息监听容器
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactor) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactor);

        //同时监听多个队列
        container.setQueues(queue001(),queue002());
        //当前消费者数量
        container.setConcurrentConsumers(1);

        //最大消费者数量
        container.setMaxConcurrentConsumers(5);

        //是否重回队列
        container.setDefaultRequeueRejected(false);

        //签收模式 自动签收
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);

        //签收策略 自定义
        container.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String queue) {

                return queue+"_"+ UUID.randomUUID().toString();
            }
        });


        //监听消息，只要有消息过来就会收到testSimpleMessageListenerContainer
        /*container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                String msg  = new String(message.getBody());
                System.out.println("消费者收到消息:============\t"+msg);
            }
        });*/

        //升级到适配器监听消息
        /*MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
        //自定义String方法:不转发到默认的方法:
        adapter.setDefaultListenerMethod("lymHandleMessage");
        */


        /*MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
        //自定义String方法:不转发到默认的方法:
        adapter.setDefaultListenerMethod("lymStringHandleMessage");
        container.setMessageListener(adapter);
        //当传过来的类型是text/plain时候，通过自定义类型转换实现
        adapter.setMessageConverter(new TextMessageConver());*/


        //实现队列绑定到方法上面
       /* MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
        adapter.setMessageConverter(new TextMessageConver());
        Map<String,String> queueOrTargetOnMessageName = new HashMap<>();
        queueOrTargetOnMessageName.put("test_spring01_topic_queue","topicMethod");
        queueOrTargetOnMessageName.put("test_spring02_direct_queue","directMethod");
        adapter.setQueueOrTagToMethodName(queueOrTargetOnMessageName);*/

       //支持json格式的转换功能
        /*MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
        adapter.setDefaultListenerMethod("jsonConvertMessage");
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        adapter.setMessageConverter(converter);*/


        //支持java对象转换
       /* MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
        adapter.setDefaultListenerMethod("javeObjectConvertMessage");

        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();

        DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();
        javaTypeMapper.setTrustedPackages("*");
        jackson2JsonMessageConverter.setJavaTypeMapper(javaTypeMapper);

        adapter.setMessageConverter(jackson2JsonMessageConverter);*/


       //全局对象转换器
        MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
        adapter.setDefaultListenerMethod("consumeMessage");

        //全局的转换器:
        ContentTypeDelegatingMessageConverter convert = new ContentTypeDelegatingMessageConverter();

        TextMessageConver textConvert = new TextMessageConver();
        convert.addDelegate("text", textConvert);
        convert.addDelegate("html/text", textConvert);
        convert.addDelegate("xml/text", textConvert);
        convert.addDelegate("text/plain", textConvert);

        Jackson2JsonMessageConverter jsonConvert = new Jackson2JsonMessageConverter();
        convert.addDelegate("json", jsonConvert);
        convert.addDelegate("application/json", jsonConvert);

        ImageMessageConverter imageConverter = new ImageMessageConverter();
        convert.addDelegate("image/png", imageConverter);
        convert.addDelegate("image", imageConverter);

        PDFMessageConverter pdfConverter = new PDFMessageConverter();
        convert.addDelegate("application/pdf", pdfConverter);


        container.setMessageListener(adapter);
      return container;
    }
}
