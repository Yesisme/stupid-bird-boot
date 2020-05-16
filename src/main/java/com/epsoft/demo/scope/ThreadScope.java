package com.epsoft.demo.scope;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ThreadScope implements Scope{

	protected static final String THREAD_SCOPE = "thread";
	
	private ThreadLocal<Map<String, Object>> beanMap = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new HashMap<>();
        }
    };
	
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Object bean = beanMap.get().get(name);
		if(Objects.isNull(bean)) {
			bean = objectFactory.getObject();
			beanMap.get().put(name, bean);
		}
		return bean;
	}

	@Override
	public Object remove(String name) {
		
		return this.beanMap.get().remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		//bean作用域范围结束的时候调用的方法，用于bean清理
		System.out.println("开始清理bean:"+name);
	}

	 /**
          * 用于解析相应的上下文数据，比如request作用域将返回request中的属性。
     */
	@Override
	public Object resolveContextualObject(String key) {
		return key;
	}

	/**
       * 作用域的会话标识，比如session作用域将是sessionId
     */
	@Override
	public String getConversationId() {
		
		return Thread.currentThread().getName();
	}

}
