package org.qf.service.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloAction {
    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "java1712";
    }
}
