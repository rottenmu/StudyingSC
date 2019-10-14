package com.cn.demo.test01.pojo;

import java.util.UUID;

public class UserInfo {
    private Integer userid;
    private String username;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public  String getId() {
    	return UUID.randomUUID().toString();
    }
}