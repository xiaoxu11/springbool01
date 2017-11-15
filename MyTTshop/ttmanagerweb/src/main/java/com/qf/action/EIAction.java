package com.qf.action;

import com.qf.dto.EIDataGridDTO;
import com.qf.dto.EITreeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EIAction {
    @RequestMapping("/eibirth")
    public String eiBirth(){
        return "ei";//返回页面
    }
    @RequestMapping("/datagrid_data.json")//因为页面中的url为：url:'datagrid_data.json'
    @ResponseBody
    public List<EIDataGridDTO> dataGrid(){
        List<EIDataGridDTO> list=new ArrayList <EIDataGridDTO>();
        list.add(new EIDataGridDTO("1001","小米",1988D));
        list.add(new EIDataGridDTO("1002","华为",2000D));
        list.add(new EIDataGridDTO("1003","锤子",2100D));
        return list;
    }
    @RequestMapping("/tree_data.json")
    @ResponseBody
    public List<EITreeDTO> tree(@RequestParam(required = false) Integer id){
        List<EITreeDTO> list = new ArrayList<EITreeDTO>();
        //JSON的嵌套对应的就是集合的嵌套
        //最外层
        EITreeDTO dto1 = new EITreeDTO(1, "Node 1", "closed");
        //第一层有嵌套
        //state：节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
        List<EITreeDTO> children = new ArrayList<EITreeDTO>();
        children.add(new EITreeDTO(11,"Node 11"));
        children.add(new EITreeDTO(12,"Node 12"));
        dto1.setChildren(children);
        list.add(dto1);
        //打开叶子节点
        //扩展了解:如果是叶子节点了，就不要再closed.否则会再次发送请求
        System.out.println(id);
        EITreeDTO dto2 = new EITreeDTO(2, "Node 2", "open");
        list.add(dto2);

        return list;
    }
}
