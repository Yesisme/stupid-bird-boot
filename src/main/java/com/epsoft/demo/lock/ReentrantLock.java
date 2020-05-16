package com.epsoft.demo.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.locks.Lock;

//@Service
public class ReentrantLock implements RedisLock{


    private StringRedisTemplate redisTemplate;

    /*
     *设定好锁对应的key
     */
    private String key;

    /*
     * 生成uuid防止,jvm中线程冲突
     */
    private final String ID_PREFIX= UUID.randomUUID().toString();

    private String releaseTime;

    public ReentrantLock(StringRedisTemplate redisTemplate,String key){
        this.redisTemplate = redisTemplate;
        this.key = key;
    }

    private static final DefaultRedisScript<Long> LOCK_SCRIPT;

    private static final DefaultRedisScript<Object> UNLOCK_SCRIPT;

    static {
        LOCK_SCRIPT = new DefaultRedisScript<>();
        LOCK_SCRIPT.setScriptSource(new ResourceScriptSource(new ClassPathResource("lock.lua")));
        LOCK_SCRIPT.setResultType(Long.class);

        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setScriptSource(new ResourceScriptSource(new ClassPathResource("unlock.lua")));

    }
    @Override
    public boolean tryLock(long releaseTime) {
        this.releaseTime = String.valueOf(releaseTime);
        Long result = redisTemplate.execute(
                LOCK_SCRIPT, Collections.singletonList(key),
                ID_PREFIX+Thread.currentThread().getId(),this.releaseTime);
        return result!=null&&result.intValue()==1;
    }

    @Override
    public void unlock() {
        redisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(key),
                ID_PREFIX+Thread.currentThread().getId(),this.releaseTime);
    }
}
