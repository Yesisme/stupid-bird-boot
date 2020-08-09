package com.epsoft.demo.lock;

public interface RedisLock {

    /**
     *
     * @param releaseTime 锁的自动释放时间
     * @return
     */
    boolean tryLock(long releaseTime);

    /**
     * 释放锁
     */
    void unlock();

}
