package com.epsoft.demo.scope;

import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ThreadScopeTest {

	public static void main(String[] args) throws InterruptedException {
		
		String path = "classpath:/beans-thread.xml";
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		
		//设置配置文件位置
		context.setConfigLocation(path);
		
		//启动容器
		context.refresh();
		
		//注册作用域
		context.getBeanFactory().registerScope(ThreadScope.THREAD_SCOPE, new ThreadScope());
		
		for(int i=0;i<2;i++) {
			new Thread(()->{
				System.out.println(Thread.currentThread()+"----------"+context.getBean("beanScopeModel"));
				System.out.println(Thread.currentThread()+"----------"+context.getBean("beanScopeModel"));	
			}).start();
			TimeUnit.SECONDS.sleep(1);
		}
	}
}
