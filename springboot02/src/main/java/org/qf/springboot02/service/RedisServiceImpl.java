package org.qf.springboot02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService{
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Override
    public void set(String key, String value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();//获取一个ValueOperations
        valueOperations.set(key,value);//存值
    }

    @Override
    public void set(String key, String value, Long time) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value,time, TimeUnit.SECONDS);//设置有效期
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
    public void lpush(String key, String value) {
        //队列的对象需要获取模版的队列对象:双向链表
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPush(key,value);//左存
    }

    @Override
    public Object rpop(String key) {
        ListOperations listOperations = redisTemplate.opsForList();
        return listOperations.rightPop(key);
    }

    @Override
    public void hset(String key, String item, String value) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key,item,value);//向hash中存值
    }

    @Override
    public Object hget(String key, String item) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        //hash对象需要单独去删，利用
//        hashOperations.delete(key,item);
        return hashOperations.get(key,item);
    }

    @Override
    public Long incr(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();//获取对象
        Long increment = valueOperations.increment(key, 1);//前者为key，后者为步长
        return increment;
    }

    @Override
    public Long decr(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Long increment = valueOperations.increment(key, -1);//递减设置为-1
        return increment;
    }

    @Override
    public void expire(String key,Long time) {
        //单独设置过期时间（例如：需要给incr增加过期时间的时候）
        redisTemplate.expire(key,time,TimeUnit.SECONDS);
    }

    @Override
    public Long ttl(String key) {
        Long expire = redisTemplate.getExpire(key);//获取剩余多少时间
        return expire;
    }


}
