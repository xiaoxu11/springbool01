package org.qf.service.service;

public interface RedisService {

    public void set(String key, String value, Long time);

    public Object get(String key);

    public void del(String key);

    public Long incr(String key);//用于计数三次错误机会

}
