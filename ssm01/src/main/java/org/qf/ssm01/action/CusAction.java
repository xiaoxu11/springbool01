package org.qf.ssm01.action;

import org.qf.ssm01.dto.CusDTO;
import org.qf.ssm01.service.CusPhoneService;
import org.qf.ssm01.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class CusAction {

    @Autowired
    private RedisService redisService;

    @Autowired
    private CusPhoneService cusPhoneService;

    @RequestMapping("/prereg")//由此方法跳转至前端页面进行操作
    public String pre(Model model){
        //系统中生成默认token显示到页面
        String token= UUID.randomUUID().toString();
        model.addAttribute("token",token);
        return "registercus";//会先找页面，再找action
    }
    @RequestMapping("/register")
    @ResponseBody
    public String register(CusDTO dto){
        cusPhoneService.addCus(dto);
        return "ok";
    }
    @RequestMapping("/getcus")
    @ResponseBody
    public CusDTO getCus(Long cid){
        CusDTO dto=cusPhoneService.getCus(cid);
        return dto;
    }
}
