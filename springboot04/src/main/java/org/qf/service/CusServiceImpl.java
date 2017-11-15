package org.qf.service;

import org.qf.dao.CusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.qf.pojo.CusSms;


import java.util.List;

@Service
public class CusServiceImpl implements CusService {
    @Autowired
    private CusDao cusDao;

    @Override
    public void registerCus(CusSms pojo) {
        cusDao.save(pojo);
    }

    @Override
    public CusSms findById(Long id) {
        return cusDao.findOne(id);
    }

    @Override
    public List<CusSms> findByPage(int page, int size) {
        Pageable pageable=new PageRequest(page,size);
        Page <CusSms> all = cusDao.findAll(pageable);
        //获取总页数
        int totalPages = all.getTotalPages();
        //获取返回的页面内容
        List <CusSms> content = all.getContent();
        return content;
    }
}
