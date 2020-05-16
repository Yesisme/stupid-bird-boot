package com.epsoft.demo.service.user.impl;


import com.alibaba.fastjson.JSON;
import com.epsoft.demo.bean.entity.Person;
import com.epsoft.demo.bean.entity.User;
import com.epsoft.demo.common.ResponseCode;
import com.epsoft.demo.common.ServerResponse;
import com.epsoft.demo.dao.UserMapper;
import com.epsoft.demo.exception.ParameterNotFound;
import com.epsoft.demo.jdk8.PredicateUser;
import com.epsoft.demo.jdk8.filterApple;
import com.epsoft.demo.lock.RedisLock;
import com.epsoft.demo.lock.RedisLockFactory;
import com.epsoft.demo.service.user.UserService;
import com.epsoft.demo.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.Collator;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static Comparator com = Collator.getInstance(java.util.Locale.CHINA);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private Person person;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedissonClient redisClient;

	private int i=0;

	ReentrantLock lock = new ReentrantLock();

	@Autowired
	private RedisLockFactory factory;

	@Override
	public Page<User> getUser(String name, String currentPage, String pageSize) {
		logger.info("请求参数{}" + name);
		PageHelper.startPage(Integer.valueOf(currentPage), Integer.valueOf(pageSize));
		Page<User> userList = (Page<User>) userMapper.selectByName(name);
		if (userList == null) {
			throw new ParameterNotFound("请输入正确的名字");
		}
		logger.info("返回参数{}" + userList);
		return userList;
	}

	@Override
	public void addUser(String userName,Integer age, String sex,String brith,Integer relationId,String email,String phone) throws InterruptedException, ParseException, ParseException {

		//redisson锁加锁
		/*RLock lock = redisClient.getLock(userName);
		try {
			lock.tryLock(3L, TimeUnit.SECONDS);
			List<User> userByName = userMapper.selectName(userName);
			System.out.println(userByName.size());
			if (userByName.size() == 0) {
				User user = new User();
				user.setUserName(userName);
				user.setAge(age);
				user.setSex(sex);
				Date stringToDate;
				stringToDate = DateUtils.stringToDate(brith, "yyyy-MM-dd");
				user.setBrith(stringToDate);
				user.setRelationId(relationId);
				user.setEmail(email);
				user.setPhone(phone);
				userMapper.insert(user);
				logger.info("返回参数:{}", user);
				logger.info("Id返回参数:{}", user.getId());
				logger.info("RelationId返回参数:{}", user.getRelationId());
			}
		} finally {
			lock.unlock();
			i++;
			System.out.println("当前请求数:" + i);
		}*/



		//单进程多线程锁
		/*try {
			lock.lock();
			List<User> userList = userMapper.selectName(userName);
			if(userList.size()==0) {
				User user = new User();
				user.setUserName(userName);
				user.setAge(age);
				user.setSex(sex);
				Date stringToDate;
				stringToDate = DateUtils.stringToDate(brith,"yyyy-MM-dd");
				user.setBrith(stringToDate);
				user.setRelationId(relationId);
				user.setEmail(email);
				user.setPhone(phone);
				userMapper.insert(user);
				logger.info("返回参数:{}",user);
				logger.info("Id返回参数:{}",user.getId());
				logger.info("RelationId返回参数:{}",user.getRelationId());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}*/

		//此锁垃圾锁不住
		/*RedisLock lock = factory.getReentrantLock("lock1");
		boolean isLock = lock.tryLock(50);
		if(!isLock){
			logger.error("获取锁失败，定时任务终止！");
		}
		try{
			logger.info("获取锁成功,开始任务！");
			List<User> userByName = userMapper.selectName(userName);
			System.out.println(userByName.size());
			if (userByName.size() == 0) {

			}
		}finally {
			logger.warn("任务执行完毕,释放锁！");
			lock.unlock();
		}*/

       /*RLock lock = redisClient.getLock(userName);
		try {
			lock.tryLock(0,50, TimeUnit.SECONDS);
			List<User> userByName = userMapper.selectName(userName);
			System.out.println(userByName.size());
			if (userByName.size() == 0) {
				User user = new User();
				user.setUserName(userName);
				user.setAge(age);
				user.setSex(sex);
				Date stringToDate;
				stringToDate = DateUtils.stringToDate(brith, "yyyy-MM-dd");
				user.setBrith(stringToDate);
				user.setRelationId(relationId);
				user.setEmail(email);
				user.setPhone(phone);
				userMapper.insert(user);
				logger.info("返回参数:{}", user);
				logger.info("Id返回参数:{}", user.getId());
				logger.info("RelationId返回参数:{}", user.getRelationId());
			}
		} finally {
			lock.unlock();
			i++;
			System.out.println("当前请求数:" + i);
		}*/

	}

	@Override
	public void addUser(User user) {
		userMapper.addUser(user);
		logger.info("返回参数:{" + user + "}");
	}
	
	@Override
	public ServerResponse<String> updateById(Long id, String sex, Integer age) {
		int count = userMapper.updateById(id, sex, age);
		if (count == 0) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "更新失败");
		}
		return ServerResponse.createBySuccessMessage("更新成功");
	}

	@Override
	public void deleteByIds(String id) {
		String[] idList = id.split(",");
		List<String> asList = Arrays.asList(idList);
		List<Integer> idInteger = new ArrayList<>();
		for (String ids : asList) {
			idInteger.add(Integer.valueOf(ids));
		}
		userMapper.deleteByIds(idInteger);
	}

	
	
	@Override
	public ServerResponse<String> selectUserName(String userName) {
		List<User> user = userMapper.selectByName(userName);
		if (user == null) {
			return ServerResponse.createByErrorMessage("用户名不存在");
		}
		return ServerResponse.createBySuccess(JSON.toJSONString(user));
	}

	@Override
	public User selectName(String userName) {
		//List>User count = userMapper.selectName(userName);
		return null;
	}

	@Override
	public Long selectById(Long id) {
		logger.info("进入SELECTById方法ID{}",id);
		Long queryId = userMapper.selectById(id);
		
		/*Collections.sort(list,new Comparator<Map<String,Object>>() {		
		@Override
		public int compare(Map<String, Object> map1, Map<String, Object> map2) {
		 return comparator.compare(map1.get("phone"), map2.get("phone"));	
		}
		});	*/
		
		/*
		 * Collections.sort(newList,new Comparator<Map<String,Object>>() {
		 * 
		 * @Override public int compare(Map<String,Object> o1, Map<String,Object> o2) {
		 * return com.compare(Integer.valueOf((String) o1.get("phone")),
		 * Integer.valueOf((String) o2.get("phone")));
		 * 
		 * } }); for (Map<String, Object> map : newList) { logger.info("list{}是",map); }
		 * return newList;
		 */
		return queryId;
	}
	
	private void sort() {
		Map<String,Object> parseObject = JSON.parseObject(person.getHnMock(), Map.class);
		System.out.println(parseObject);

		List<Map> parseArray = JSON.parseArray(JSON.toJSONString(parseObject.get("data")), Map.class);
		List list = new ArrayList<>();
		for (Map map : parseArray) {
			System.out.println(map);
		}
		Collections.sort(parseArray, new Comparator<Map>() {

			@Override
			public int compare(Map o1, Map o2) {
				if(Integer.valueOf(String.valueOf(o2.get("year"))).compareTo(Integer.valueOf(String.valueOf(o1.get("year"))))!=0) {
					return Integer.valueOf(String.valueOf(o2.get("year"))).compareTo(Integer.valueOf(String.valueOf(o1.get("year"))));
				}else {
					return Integer.valueOf(String.valueOf(o2.get("month"))).compareTo(Integer.valueOf(String.valueOf(o1.get("month"))));
				}
			}
		});
		for (Object object : parseArray) {
			System.out.println("排序后"+object);
		}
	}

	@Override
	public void mockTcp(){
		try {
			ServerSocket socket = new ServerSocket(33333);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> queryUserList() {
		List<User> queryAllUser = userMapper.queryAllUser();
		//List<User> list = AboutApple.FilterApps(list,(User u)-> "男".equals(u.getSex())||u.getAge()>22);
		//List<User> list = queryAllUser.stream().filter((User u) -> u.getAge()>60).collect(Collectors.toList());	
		//java8实现
		List list = filterApple.filterApples(queryAllUser, PredicateUser::filter);
		
		//传统实现
		/*PredicateUser p = new PredicateUser();
		List<User> list = filterApple.filterApples(queryAllUser, p);*/
		return list;
	}
}
