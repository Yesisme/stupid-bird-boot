package com.epsoft.demo.quartz;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloSheduler {

	public static void main(String[] args) throws Exception {
		//创建一个jobDetai实例。将该实例与helloJob绑定 基于Builder模式 创建名字组名
		JobDetail jobDetail = JobBuilder
				.newJob(JobExecutionContext.class)
				.withIdentity("myJob", "group1")
				.usingJobData("message", "myJob1")
				.usingJobData("FloatValue", 3.14F)
				.build();

		Date date = new Date();
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("currentTime is"+sdft.format(date));
		date.setTime(date.getTime()+3000);
		
		Date endDate = new Date();
		endDate.setTime(endDate.getTime()+6000);
		
		// 创建一个Trigger实例。定义job立即实行。并且每隔两秒钟重复执行一次。直到永远
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("triggrt", "group1")
				.usingJobData("message","trigger1")
				.usingJobData("doubleValue",3.5D)
				.startAt(date)
				.endAt(endDate)
				.withSchedule(
						SimpleScheduleBuilder
								.simpleSchedule()
								.withIntervalInSeconds(2)
								.repeatForever())//.withRepeatCount();执行的次数
								.build();
		//创建Scheduler实例
		SchedulerFactory sfact = new StdSchedulerFactory();
		Scheduler scheduler = sfact.getScheduler();
		scheduler.start();		
		//打印当前时间，格式
		scheduler.scheduleJob(jobDetail, trigger);
}
}