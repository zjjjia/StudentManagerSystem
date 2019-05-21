package com.example.studentmanagersystem.student.presenter.IView;

import com.example.studentmanagersystem.base.IView;
import com.example.studentmanagersystem.entity.Course;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

public interface IChooseCourseView extends IView {

    void loadCourseSuccess(List<Course> courseList);

    void onError(Throwable e);

    void choseSuccess();
}
