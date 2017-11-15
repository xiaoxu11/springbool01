package org.qf.ssm01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void set(String key, Object value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value);
    }

    @Override
    public Long incr(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Long increment = valueOperations.increment(key, 1);//对key计数并设置步长为1
        return increment;//返回计数次数
    }
    //在redis存值，并设置时间限制
    @Override
    public void set(String key, String value,Long time) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value,time, TimeUnit.SECONDS);
    }
    //在redis取值
    @Override
    public Object get(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
}
