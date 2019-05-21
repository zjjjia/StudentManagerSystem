package com.example.studentmanagersystem.entity;


import cn.bmob.v3.BmobObject;

public class Grade extends BmobObject {
    private String studentId;
    private String courseId;
    private Integer grade;

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

    @Override
    public String toString() {
        return "Grade{" +
                "studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", grade=" + grade +
                '}';
    }
}
