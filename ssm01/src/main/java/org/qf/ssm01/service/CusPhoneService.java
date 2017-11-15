package org.qf.ssm01.service;

import org.qf.ssm01.dto.CusDTO;
import org.qf.ssm01.pojo.CusPhone;

public interface CusPhoneService {
    public void addCus(CusDTO dto);

    public CusDTO getCus(Long cid);

    public CusDTO getCusByToken(String token);
}
