package com.epsoft.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 线程池
 * @author Administrator
 *
 */
@Slf4j
public class ThreadPool {

	private static final int PRODUCE_TASK_SLEEP_Time =2;
	private static final int PRODUCE_TASK_MAX_NUMBER =10;
	
	public static class threadPoolTask implements Runnable {
		
		private Object taskData;
		public threadPoolTask(Object target) {
			this.taskData=target;
		}
		
		@Override
		public void run() {
			
			System.out.println("开始执行任务"+taskData);
			
			try {
				Thread.sleep(10);
			}catch(Exception e) {
				e.printStackTrace();
			}
			taskData=null;
		}
		
		public Object getTask() {
			return this.taskData;
		}
	}
	
	public static void main(String[] args) {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.MINUTES.SECONDS, new ArrayBlockingQueue<>(3),new ThreadPoolExecutor.AbortPolicy());
		for (int i = 0; i <= PRODUCE_TASK_MAX_NUMBER; i++) {
			try {
				String task = "task@"+i;
				System.out.println("put"+task);
				threadPool.execute(new threadPoolTask(task));
				Thread.sleep(PRODUCE_TASK_SLEEP_Time);
			}catch(Exception e) {
				log.info(e.getMessage());
				e.printStackTrace();
			}
			
		}
	}
}
