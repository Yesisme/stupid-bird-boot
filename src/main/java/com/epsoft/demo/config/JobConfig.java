package com.epsoft.demo.config;

import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.epsoft.demo.job.CoreJobMessageJob;

@Configuration
public class JobConfig {

	@Bean(name="jobMessageJobBean")
	public MethodInvokingJobDetailFactoryBean jobMessageJobBean(CoreJobMessageJob coreJobMessageJob) {
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
		jobDetail.setConcurrent(false);
		jobDetail.setName("general-jobMessageJob");
		jobDetail.setGroup("general");
		jobDetail.setTargetObject(coreJobMessageJob);
		jobDetail.setTargetMethod("queryUser");
		return jobDetail;
	}

	@Bean(name="jobMessageBeanTrigger")
	public CronTriggerFactoryBean jobMessageJobTrigger(@Qualifier("jobMessageJobBean") MethodInvokingJobDetailFactoryBean jobMessageJobBean) {
		CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
		tigger.setJobDetail(jobMessageJobBean.getObject());
		tigger.setCronExpression("* * 3 * * ?");
		tigger.setName("general-jobMessageBeanTrigger");
		return tigger;
	}

	@Bean(name="schedulerFactory")
	public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("jobMessageBeanTrigger")Trigger jobMessageBeanTrigger){
		SchedulerFactoryBean bean = new SchedulerFactoryBean() ;
		bean.setOverwriteExistingJobs(true);
		bean.setStartupDelay(15);
		bean.setTriggers(jobMessageBeanTrigger);
		return bean;
	}

}
