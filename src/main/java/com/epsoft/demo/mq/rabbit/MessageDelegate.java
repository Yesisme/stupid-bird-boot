package com.epsoft.demo.mq.rabbit;

import com.alibaba.fastjson.JSON;
import com.epsoft.demo.bean.entity.User;

import java.util.Map;

public class MessageDelegate {

    public void handleMessage(byte[] messageBody){
        System.out.println("默认方法：消息内容"+new String(messageBody));
    }

    public void lymHandleMessage(byte[] messageBody){
        System.out.println("自定义的默认方法：消息内容"+messageBody);
    }

    //接收字符串的消息
    public void lymStringHandleMessage(String message){
        System.out.println("自定义的String方法：消息内容"+message);
    }

    //消息队列映射方法
    public void topicMethod(String message){
        System.out.println("topicMethod\t收到消息:"+message);
    }
    //消息队列映射方法
    public void directMethod(String message){
        System.out.println("directMethod\t收到消息:"+message);
    }

    //json格式的转换器
    public void jsonConvertMessage(Map message){
        System.out.println("json格式转换方法\t收到消息:"+message);
    }

    //javad对象格式的转换器
    public void javeObjectConvertMessage(User user){
        System.out.println("java对象格式转换方法\t收到消息:"+ JSON.toJSONString(user));
    }
}
