package com.example.studentmanagersystem.entity;

import cn.bmob.v3.BmobObject;

public class Course extends BmobObject {

    private String courseName;
    private String location;
    private String time;
    private String teacherId;
    private String userName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

}
