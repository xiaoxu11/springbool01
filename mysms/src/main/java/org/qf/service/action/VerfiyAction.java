package org.qf.service.action;

import org.qf.service.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证验证码的类
 */
@Controller
public class VerfiyAction {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/verfiy")
    @ResponseBody
    public Map<String,Object> verfiy(String mobile, String code){
        Map<String,Object> map=new HashMap <String,Object>();
        String serverCode = (String) redisService.get(mobile);
        if(code==null){
            map.put("msg","验证码不能为空");
            return map;
        }
        if(code.equals(serverCode)){
            redisService.del(mobile);
            map.put("mobile",mobile);
            map.put("status",true);
            return map;
        }else {
            Long incr = redisService.incr("error_"+mobile);
            if(incr>=3){
                redisService.del(mobile);
                map.put("msg","验证次数过多，请重新验证");
                map.put("status",false);
            }else {
                map.put("msg","验证错误,您还有"+(3-incr)+"次机会，请重新验证");
                map.put("status",false);
            }
        }
        return map;
    }
}
