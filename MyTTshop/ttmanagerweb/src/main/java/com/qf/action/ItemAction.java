package com.qf.action;

import com.qf.pojo.TbItem;
import com.qf.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemAction {

    @Autowired
    private ItemService itemService;
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem findByItem(@PathVariable Long itemId){
        return itemService.findById(itemId);
    }
}
