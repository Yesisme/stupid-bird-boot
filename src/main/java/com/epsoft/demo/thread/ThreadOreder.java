package com.epsoft.demo.thread;

import edu.emory.mathcs.backport.java.util.concurrent.ExecutorService;
import edu.emory.mathcs.backport.java.util.concurrent.Executors;
/**
 * 线程执行顺序
 * @author Administrator
 *
 */
class testThreadOrder{
	static Thread thread1 = new Thread(new Runnable() {
		@Override
		public void run() {
			System.out.println("i am is thread1");
		}
	});
	
	static Thread thread2 = new Thread(new Runnable() {
		@Override
		public void run() {
			System.out.println("i am is thread2");
		}
	});
	
	static Thread thread3 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			System.out.println("i am is thread3");
		}
	});
	
	static ExecutorService execute = Executors.newSingleThreadExecutor();
}
public class ThreadOreder {

	public static void main(String[] args) throws InterruptedException {
		//join保证了线程的执行顺序。运行了object的wait方法 让主线程保证子线程结束之后才能继续运行
		/*testThreadOrder.thread1.start();
		testThreadOrder.thread1.join();
		testThreadOrder.thread2.start();
		testThreadOrder.thread2.join();
		testThreadOrder.thread3.start();*/
		//第二种方式 实现通过fifo
		testThreadOrder.execute.submit(testThreadOrder.thread1);
		testThreadOrder.execute.submit(testThreadOrder.thread2);
		testThreadOrder.execute.submit(testThreadOrder.thread3);
		testThreadOrder.execute.shutdown();
	}
}
