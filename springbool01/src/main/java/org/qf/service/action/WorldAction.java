package org.qf.service.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WorldAction {
    //@RestController注解直接支持json响应
    //不用在写@responsebody注解
    @RequestMapping("/world")
    public Map<String,Object> world(){
        Map<String,Object> map=new HashMap <>();
        map.put("hello","world");
        return map;
    }
}
