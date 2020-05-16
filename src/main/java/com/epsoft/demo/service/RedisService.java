package com.epsoft.demo.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.epsoft.demo.bean.entity.User;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

@Component
public class RedisService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	public void add (String key,Object object,Long time) {
		Gson gson = new Gson();
		stringRedisTemplate.opsForValue().set(key, gson.toJson(object), time,TimeUnit.MINUTES);
	}
	
	public void add(String key,List<Object> list,Long time) {
		Gson gson = new Gson();
		String src = gson.toJson(list);
		stringRedisTemplate.opsForValue().set(key, src, time,TimeUnit.MINUTES);
	}
	
	public Object get(String key) {
		if(StringUtils.isNotEmpty(key)) {
			return new Gson().fromJson(stringRedisTemplate.opsForValue().get(key), Object.class);
		}
		return null;
	}
	
	public Object getList(String key) {
		String source = stringRedisTemplate.opsForValue().get(key);
		if(StringUtils.isNotEmpty(key)) {
			return new Gson().fromJson(source, new TypeToken<List<Object>>() {
			}.getType());
		}
		return null;
	}
	
	public void delete(String key) {
		stringRedisTemplate.opsForValue().getOperations().delete(key);
	}
}
