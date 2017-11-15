package com.qf.service;

import com.qf.dao.TbItemMapper;
import com.qf.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Override
    public TbItem findById(Long itemId) {
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
        return tbItem;
    }
}
