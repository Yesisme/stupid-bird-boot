package com.epsoft.demo.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

	@Override
	public void execute(JobExecutionContext context)throws JobExecutionException {
		Date date = new Date();
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("currentTime is"+sdft.format(date));
		//当前的业务逻辑
		System.out.println("hello world");
	}

	
}
