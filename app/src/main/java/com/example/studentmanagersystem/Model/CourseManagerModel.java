package com.example.studentmanagersystem.Model;

import com.example.studentmanagersystem.bmboTable.Course;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CourseManagerModel {

    public void loadCourseInfo(Integer userId, final CourseManagerCallback callback) {
        BmobQuery<Course> query = new BmobQuery<>();
        query.addWhereEqualTo("userId", userId);
        query.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> list, BmobException e) {
                if (e == null) {
                    callback.querySuccess(list);
                } else {
                    callback.queryFailed(e);
                }
            }
        });
    }

    public interface CourseManagerCallback{
        void querySuccess(List<Course> courseList);

        void queryFailed(BmobException e);
    }
}
