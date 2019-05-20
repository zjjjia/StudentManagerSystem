package com.example.studentmanagersystem.student.model;

import com.example.studentmanagersystem.Utils.LogUtil;
import com.example.studentmanagersystem.entity.Course;
import com.example.studentmanagersystem.entity.Grade;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CourseModel {

    private static final String TAG = "CourseModel";

    public void queryCourseList(String studentId, final CourseCallback<List<Course>> callback){
        BmobQuery<Course> query = new BmobQuery<>();
        query.addWhereEqualTo("studentId", studentId);
        query.include("Course[courseName],User[userName]");
        query.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> list, BmobException e) {
                if(e == null){
                    LogUtil.d(TAG, "done: " + list.toString());
                    callback.querySuccess(list);
                }else {
                    callback.onError(e);
                }
            }
        });
    }

    public interface CourseCallback<T>{
        void querySuccess(T result);

        void onError(BmobException e);
    }
}
