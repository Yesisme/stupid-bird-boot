package com.epsoft.demo.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;

public class JobExecutionContext implements Job {
	
	private String message;
	

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public void execute(
			org.quartz.JobExecutionContext context)
			throws JobExecutionException {
		//获取组名 名字
		JobKey jobDetail = context.getJobDetail().getKey();
		System.out.println("group is+"+jobDetail.getGroup());
		System.out.println("name is+"+jobDetail.getName());
		//获取值
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String message1 = jobDataMap.getString("message");
		Float floatValue = jobDataMap.getFloat("FloatValue");
		System.out.println("message is"+message);
		System.out.println("floatValue is"+floatValue);
		
		JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
		String mergeMessage = mergedJobDataMap.getString("message");//优先打印trigger信息
		System.out.println(mergeMessage);
		
		Trigger trigger = context.getTrigger();//通过触发器获取开始时间结束时间
		JobKey jobKey = trigger.getJobKey();
		System.out.println("startTime is"+trigger.getStartTime());
		System.out.println("endTime is"+trigger.getEndTime());
		System.out.println(jobKey.getName()+jobKey.getGroup());
	}

	
}
