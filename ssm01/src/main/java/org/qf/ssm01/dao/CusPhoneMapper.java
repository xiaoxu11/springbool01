package org.qf.ssm01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.qf.ssm01.pojo.CusPhone;
import org.qf.ssm01.pojo.CusPhoneExample;
@Mapper
public interface CusPhoneMapper {
    int countByExample(CusPhoneExample example);

    int deleteByExample(CusPhoneExample example);

    int deleteByPrimaryKey(Long cid);

    int insert(CusPhone record);

    int insertSelective(CusPhone record);

    List<CusPhone> selectByExample(CusPhoneExample example);

    CusPhone selectByPrimaryKey(Long cid);

    int updateByExampleSelective(@Param("record") CusPhone record, @Param("example") CusPhoneExample example);

    int updateByExample(@Param("record") CusPhone record, @Param("example") CusPhoneExample example);

    int updateByPrimaryKeySelective(CusPhone record);

    int updateByPrimaryKey(CusPhone record);
}