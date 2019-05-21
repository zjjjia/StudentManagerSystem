package com.example.studentmanagersystem.teacher.presenter.IView;

import com.example.studentmanagersystem.base.IView;
import com.example.studentmanagersystem.entity.ChoseCourse;
import com.example.studentmanagersystem.entity.Course;

import java.util.List;

public interface IGradeManagerView extends IView {

    void loadCourseSuccess(List<Course> list);

    void  onError(Throwable e);

    void loadGradeEditInfo(List<ChoseCourse> list);

    void saveGradeSuccess();
}
