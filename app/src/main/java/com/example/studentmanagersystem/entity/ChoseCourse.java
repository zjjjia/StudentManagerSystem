package com.example.studentmanagersystem.entity;

import android.content.Intent;

import cn.bmob.v3.BmobObject;

public class ChoseCourse extends BmobObject {

    private String studentId;   //user表中的objectId
    private String courseId;    //course表中的objectId
    private Integer grade;
    private String studentName;
    private String userId;           //学号

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ChoseCourse{" +
                "studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", grade=" + grade +
                ", studentName='" + studentName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
