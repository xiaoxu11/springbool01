package org.qf.ssm01.action;

import org.qf.ssm01.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class VerfiyAction {

    @Autowired
    private RedisService redisService;//为了调用get方法从缓存中获取验证码

    //获取用户的手机号和动态码
    @RequestMapping("/verfiy")
    @ResponseBody
    public Map<String,Object> verfiy(String mobile, String code){//因为要显示到页面，故需要返回json数据
        Map<String,Object> map=new HashMap <String,Object>();
        //服务段发送的验证码从分布式的缓存中取记录
        String serverCode=(String)redisService.get(mobile);//获取服务端存储的验证码
        if(serverCode==null||"".equals(serverCode.trim())){//防止当缓存中的验证码为空时依旧访问
            map.put("status",false);
            map.put("error","验证过多,必须重新获取验证码");
            return map;
        }
        if(code.equals(serverCode)) {//将输入的code与缓存中的验证码相比较
            //如果相等，验证成功
            //在验证成功后立刻删除缓存
            redisService.del(mobile);
            map.put("status",true);
            return map;
        }else {
            //如果不相等，验证失败
            //计数三次
            //分布式环境需要进行分布式计数
            Long incr = redisService.incr("error_" + mobile);
            if(incr>=3){
                //验证过多,必须要用户重新获取验证码，并删除缓存
                redisService.del(mobile);//清除缓存
                redisService.del("error_" + mobile);//清楚错误缓存
                map.put("status",false);
                map.put("error","验证过多,必须重新获取验证码");
            }else {
                //验证失败
                map.put("status",false);
                map.put("error","验证错误,您还有"+(3-incr)+"次机会，请重新验证");
            }
        }
        return map;
    }
}
