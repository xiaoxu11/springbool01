package org.qf.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    //存储有时间生存的
    @Override
    public void set(String key, String value, Long time) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value,time, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Long incr(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Long increment = valueOperations.increment(key, 1);
        return increment;
    }
}
