package com.example.studentmanagersystem.student.presenter.IView;

import com.example.studentmanagersystem.base.IView;
import com.example.studentmanagersystem.entity.Course;

import java.util.List;

import cn.bmob.v3.exception.BmobException;


public interface ICourseView extends IView {

    void loadCourseInfo(List<Course> courseList);

    void onError(BmobException e);
}
