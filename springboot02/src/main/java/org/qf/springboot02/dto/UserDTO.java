package org.qf.springboot02.dto;

public class UserDTO {
    private Long id;
    private String uname;
    private String upassword;

    public UserDTO() {
    }

    public UserDTO(Long id, String uname, String upassword) {
        this.id = id;
        this.uname = uname;
        this.upassword = upassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }
}
