package org.qf.ssm01.service;

import org.qf.ssm01.dao.CusPhoneMapper;
import org.qf.ssm01.dto.CusDTO;
import org.qf.ssm01.pojo.CusPhone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CusPhoneServiceImpl implements CusPhoneService {
    @Autowired
    private CusPhoneMapper cusPhoneMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void addCus(CusDTO dto) {
        CusPhone pojo = new CusPhone();
        //对象属性直接复制，需条件类型均一致
        //用于将对象中的数据赋值给另一个对象
        BeanUtils.copyProperties(dto, pojo);
        cusPhoneMapper.insert(pojo);
    }

    @Override
    public CusDTO getCus(Long cid) {
        CusPhone pojo = cusPhoneMapper.selectByPrimaryKey(cid);
        CusDTO dto = new CusDTO();
        BeanUtils.copyProperties(pojo, dto);
//        cusPhoneMapper.selectByExample()
        return dto;
    }

    @Override
    public CusDTO getCusByToken(String token) {
        //先从缓存中判断是否有记录
        Object o = redisService.get(token);
        if(o==null) {//缓存中没有的时候
            //没有再从数据库取
            CusDTO cus = getCus(1L);//模拟根据token查询，实际不应用此方法
            //取得数据再存入缓存
            redisService.set(token,cus);
            return cus;
        }
        //当缓存中有记录是，直接进行返回
        return (CusDTO)o;
    }

}
