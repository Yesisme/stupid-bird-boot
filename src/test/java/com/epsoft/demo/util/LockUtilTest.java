package com.epsoft.demo.util;

import org.junit.Test;

import com.epsoft.demo.utils.LockUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LockUtilTest {

	//测试重复获取和重复释放
	@Test
	public void test1() throws Exception {
		String lockKey = "key1";
		for(int i=0;i<10;i++) {
			LockUtils.lock(lockKey,10000L,1000);
		}
		
		for(int i=0;i<9;i++) {
			LockUtils.unlock(lockKey);
		}
	}
	
	//获取之后不释放，超时之后被thread1获取
    @Test
    public void test2() throws Exception {
        String lock_key = "key3";
        LockUtils.lock(lock_key, 5000L, 1000);
        Thread thread1 = new Thread(() -> {
            try {
                try {
                	LockUtils.lock(lock_key, 5000L, 7000);
                } finally {
                	LockUtils.unlock(lock_key);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread1.setName("thread1");
        thread1.start();
        thread1.join();
    }
	
}
