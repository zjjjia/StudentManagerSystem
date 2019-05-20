package com.example.studentmanagersystem.entity;

import cn.bmob.v3.BmobObject;

public class Grade extends BmobObject {

    private String studentId;   //user表中的objectId
    private String courseId;    //course表中的objectId
    private Integer grade;
    private String teacherId;   //user表中的objectId


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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", grade=" + grade +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }
}
