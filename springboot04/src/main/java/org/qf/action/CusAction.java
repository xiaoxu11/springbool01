package org.qf.action;

import org.qf.service.CusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.qf.pojo.CusSms;

import java.util.List;
import java.util.UUID;

@Controller
public class CusAction {

    @Autowired
    private CusService cusService;

    @RequestMapping("/reg")
    @ResponseBody
    public String reg(){
        CusSms cus=new CusSms();
        cus.setCname("如梦团队");
        cus.setToken(UUID.randomUUID().toString());
        cusService.registerCus(cus);
        return "ok";
    }

    @RequestMapping("/find")
    @ResponseBody
    public CusSms find(Long id){
        return cusService.findById(id);
    }

    @RequestMapping("/findpage")
    @ResponseBody
    public List<CusSms> findPage(Integer page, Integer size){
        //注意page从0开始
        return cusService.findByPage(page,size);
    }
}
