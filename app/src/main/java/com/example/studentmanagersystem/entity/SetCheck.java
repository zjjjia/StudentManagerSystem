package com.example.studentmanagersystem.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobGeoPoint;

public class SetCheck extends BmobObject {

    private String teacherId;
    private BmobGeoPoint point;
    private Long startTime;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public BmobGeoPoint getPoint() {
        return point;
    }

    public void setPoint(BmobGeoPoint point) {
        this.point = point;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "SetCheck{" +
                "teacherId=" + teacherId +
                ", point=" + point +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
