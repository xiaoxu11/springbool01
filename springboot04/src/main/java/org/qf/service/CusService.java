package org.qf.service;

import org.qf.pojo.CusSms;

import java.util.List;

public interface CusService {
    public void registerCus(CusSms pojo);

    public CusSms findById(Long id);

    public List<CusSms> findByPage(int page, int size);
}
