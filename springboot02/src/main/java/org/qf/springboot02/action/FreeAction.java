package org.qf.springboot02.action;

import org.qf.springboot02.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FreeAction {
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("hello","如梦永森手拉手");
        return "index";
    }

    @RequestMapping("/list")
    public ModelAndView showList(){
        ModelAndView mv=new ModelAndView();
        List<UserDTO> list=new ArrayList <UserDTO>();
        list.add(new UserDTO(1L,"Java1712","班主任"));
        list.add(new UserDTO(2L,"Java1711","123"));
        list.add(new UserDTO(3L,"Java1709","456"));
        mv.addObject("userList",list);//将集合存入mv中
        mv.setViewName("userlist");//设置转入的页面名字
        return mv;
    }
}
