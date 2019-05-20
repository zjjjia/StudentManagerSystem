package com.example.studentmanagersystem.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobGeoPoint;

public class Check extends BmobObject {

    private Integer studentId;
    private String userName;
    private BmobGeoPoint point;
    private String checkKey;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BmobGeoPoint getPoint() {
        return point;
    }

    public void setPoint(BmobGeoPoint point) {
        this.point = point;
    }

    public String getCheckKey() {
        return checkKey;
    }

    public void setCheckKey(String checkKey) {
        this.checkKey = checkKey;
    }


    @Override
    public String toString() {
        return "Check{" +
                "studentId=" + studentId +
                ", point=" + point +
                ", checkKey=" + checkKey + '\'' +
                '}';
    }
}
