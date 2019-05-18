package com.example.studentmanagersystem.entity;


import cn.bmob.v3.BmobObject;

public class User extends BmobObject {

    private Integer userId;
    private String userName;
    private String password;
    /**
     * 账号权限，区分老师账号和学生账号；0表示老师，1表示学生
     */
    private Integer permission;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }
}
