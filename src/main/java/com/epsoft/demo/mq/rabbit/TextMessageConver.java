package com.epsoft.demo.mq.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

public class TextMessageConver implements MessageConverter {


    //java对象转message对象
    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        return new Message(object.toString().getBytes(),messageProperties);
    }

    //message对象转成java对象
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        String contentType =message.getMessageProperties().getContentType();
        if(null!=contentType && "text/plain".equals(contentType)){
            return new String (message.getBody());
        }
        return message;
    }
}
