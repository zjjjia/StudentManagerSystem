package com.example.studentmanagersystem.teacher.model;

import com.example.studentmanagersystem.entity.Course;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class CourseManagerModel {

    public void loadCourseInfo(String teacherId, final CourseManagerCallback callback) {
        BmobQuery<Course> query = new BmobQuery<>();
        query.addWhereEqualTo("teacherId", teacherId);
        query.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> list, BmobException e) {
                if (e == null) {
                    callback.querySuccess(list);
                } else {
                    callback.onError(e);
                }
            }
        });
    }

    public void addCourseInfo(String courseName, String courseTime, String teacherId, final CourseManagerCallback callback) {
        Course course = new Course();
        course.setCourseName(courseName);
        course.setTime(courseTime);
        course.setTeacherId(teacherId);
        course.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    callback.onSuccess();
                } else {
                    callback.onError(e);
                }
            }
        });
    }

    public interface CourseManagerCallback {
        void querySuccess(List<Course> courseList);

        void onError(BmobException e);

        void onSuccess();
    }

}
