package com.ray.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     *
     * @param key productId
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key,String value){
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){//setnx 如果可以设置 返回1（true）
            return true;//锁住
        }
        //如果被锁代码抛出异常则会出现死锁，所以必须有如下判断
        String currentValue = redisTemplate.opsForValue().get(key);
        //如果锁过期（存储时间小于当前时间）
        if(!StringUtils.isEmpty(currentValue)
                &&Long.parseLong(currentValue)<System.currentTimeMillis()){
            //获取上一个锁的时间，使用getset //redis的单线程导致多线程情况下下列代码只有一个线程执行
            String oldValue = redisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(oldValue)&&oldValue.equals(currentValue)){
                return true;
            }
        }
        return false;//不能设置，返回0（false）
    }

    public  void unlock(String key,String value){
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue)&&currentValue.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("【redis分布式锁】解锁异常，{}",e);
        }
    }
}
