package org.qf.pojo;

import javax.persistence.*;

//表级别的映射
@Table(name="cus_sms")//指明表的名字
@Entity
public class CusSms {

    //映射主键级别-OID
    @Id//表示为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//指明主键的生成方式
    private Long cid;

    //普通字段映射:默认的字段名称就是属性名称，所以一般不用写
    private String cname;
    private String token;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
