package com.epsoft.demo.job;

import com.epsoft.demo.bean.entity.User;
import com.epsoft.demo.dao.UserMapper;
import com.epsoft.demo.lock.RedisLock;
import com.epsoft.demo.lock.RedisLockFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Component
@EnableScheduling
@Slf4j
public class CoreJobMessageJob /*implements CommandLineRunner*/ {

	@Autowired
	private RedisLockFactory factory;

	@Autowired
	private UserMapper userMapper;

	public void queryUser() throws InterruptedException {
		RedisLock lock = factory.getReentrantLock("lock1");
		boolean isLock = lock.tryLock(50);
		if(!isLock){
			log.error("获取锁失败，定时任务终止！");
		}

		try{
			log.info("获取锁成功,开始任务！");
			queryInset();
		}finally {
			log.warn("任务执行完毕,释放锁！");
			lock.unlock();
		}

	}

	public void queryInset() throws InterruptedException {
		log.info("开始查询数据库[{}]","==========================");
		List<User> users = userMapper.selectByName("崽");
		if(users.size()==0){
			User user = new User();
			user.setUserName("崽");
			user.setAge(new Random().nextInt(10)+1);
			user.setBrith(new Date());
			user.setPhone(134534534+String.valueOf(user.getAge()));
			user.setRelationId(1);
			userMapper.insert(user);
			log.info("插入数据成功[{}]","==========================");
		}
		Thread.sleep(500);
	}
	/*@Override
	public void run(String... args) throws Exception {
		queryUser();
	}*/

	//程序未启动完成执行
	/*@PostConstruct
	public void init(){
		queryUser();
	}*/
	public static void main(String[] args) {
		System.out.println(new Random().nextInt(10)+1);
	}
}
