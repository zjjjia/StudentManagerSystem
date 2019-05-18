package com.example.studentmanagersystem.entity;

import cn.bmob.v3.BmobObject;

public class Grade extends BmobObject {

    private Integer userId;
    private Integer courseId;
    private Integer grade;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
