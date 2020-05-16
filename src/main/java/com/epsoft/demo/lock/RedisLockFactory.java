package com.epsoft.demo.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisLockFactory {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public RedisLock getReentrantLock(String key){
        return new ReentrantLock(redisTemplate,key);
    }

}
