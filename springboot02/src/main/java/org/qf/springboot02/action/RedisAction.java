package org.qf.springboot02.action;

import org.qf.springboot02.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RedisAction {

    //得到自定义配置的内容
    //@value中要对应配置文件中的key
    @Value("${java1712}")
    private String java1712;

    //注入服务层
    @Autowired
    private RedisService redisService;

    @RequestMapping("/java")
    public String java(){
        return java1712;
    }

    @RequestMapping("/set")
    public String set(String key,String value){
        redisService.set(key,value);
        return "ok";
    }
    @RequestMapping("/get")
    public Object getValue(String key){
        return redisService.get(key);
    }
    @RequestMapping("/lpush")
    public String lpush(String key,String value){
        redisService.lpush(key,value);
        return "ok";
    }
    @RequestMapping("/rpop")
    public Object rpop(String key){
        return redisService.rpop(key);
    }
    @RequestMapping("/hset")
    public Map<String,Object> hset(String key,String item,String value){
        Map<String,Object> map=new HashMap <String,Object>();
        redisService.hset(key,item,value);
        //每个响应码对应一种状态
        map.put("code","0");//返回响应码
        return map;
    }
    @RequestMapping("/hget")
    public Map<String,Object> hget(String key,String item){
        Map<String,Object> map=new HashMap <String,Object>();
        Object hget = redisService.hget(key, item);
        //每个响应码对应一种状态
        if(hget!=null) {
            map.put("code", "ok");//返回响应码
            map.put("data",hget);//将获得的值返回
        }else {
            map.put("code","false");
        }
            return map;
    }
    //设置有效期
    @RequestMapping("/setExp")
    public Map<String,Object> setExp(String key,String value,Long time){
        Map<String,Object> map=new HashMap <String,Object>();
        redisService.set(key,value,time);
        map.put("code","ok");//返回响应码
        return map;
    }
    //做统计(注：因为有@RestController注解，故不用加responseBody注解)
    @RequestMapping("/incr")
    public Map<String,Object> incr(String key){
        Map<String,Object> map=new HashMap <String, Object>();
        //调用服务层进行分布式计数(增)
        Long incr = redisService.incr(key);
        map.put("code","ok");//返回响应码
        map.put("data",incr);
        return map;
    }
    @RequestMapping("/decr")
    public Map<String,Object> decr(String key){
        Map<String,Object> map=new HashMap <String, Object>();
        //调用服务层进行分布式计数(减)
        Long incr = redisService.decr(key);
        map.put("code","ok");//返回响应码
        map.put("data",incr);
        return map;
    }
    //单独的过期时间
    @RequestMapping("/exp")
    public Map<String, Object> exp(String key,Long time){
        Map<String,Object> map=new HashMap<String, Object>();
        redisService.expire(key,time);//对独立的key设置时间
        map.put("code","ok");
        return map;
    }
    //查看剩余时间
    @RequestMapping("/ttl")
    public Map<String, Object> ttl(String key){
        Map<String,Object> map=new HashMap<String, Object>();
        Long ttl = redisService.ttl(key);//对独立的key设置时间
        map.put("code","ok");
        map.put("data",ttl);//方便查看ttl剩余时间
        return map;
    }

}
