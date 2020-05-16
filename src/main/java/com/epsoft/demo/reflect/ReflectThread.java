package com.epsoft.demo.reflect;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ReflectThread implements CmdExecutor{
	
	private ThreadPoolExecutor threadPoolExecutor;
	
	public ReflectThread() {
		this.threadPoolExecutor = new ThreadPoolExecutor(300, 500,0L,TimeUnit.MILLISECONDS,
		         new LinkedBlockingQueue<Runnable>(100), new ThreadPoolExecutor.AbortPolicy());
	}

	@Override
	public void submit(AbstartJob job) {
		threadPoolExecutor.execute(job);
	}
}
