package org.qf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.qf.pojo.CusSms;

public interface CusDao extends JpaRepository<CusSms,Long> {//前者是pojo的具体类型，后者为主键的id类型
    //dao已开发完成，基本的CRUD功能已经具备
}
