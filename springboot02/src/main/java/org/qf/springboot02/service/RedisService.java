package org.qf.springboot02.service;

public interface RedisService {

    public void set(String key,String value);

    //设置有效期
    public void set(String key,String value,Long time);

    public Object get(String key);

    public void del(String key);

    public void lpush(String key,String value);

    public Object rpop(String key);

    public void hset(String key,String value,String item);//注意加item

    public Object hget(String key,String item);//hash类型存储的时候需要多一项子键

    public Long incr(String key);

    public Long decr(String key);

    public void expire(String key,Long time);

    public Long ttl(String key);
}
