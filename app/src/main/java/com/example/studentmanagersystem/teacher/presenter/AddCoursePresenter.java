package com.example.studentmanagersystem.teacher.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.studentmanagersystem.teacher.model.CourseManagerModel;
import com.example.studentmanagersystem.Utils.LogUtil;
import com.example.studentmanagersystem.base.BasePresenter;
import com.example.studentmanagersystem.entity.Course;
import com.example.studentmanagersystem.teacher.presenter.IView.IAddCourseView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;


public class AddCoursePresenter extends BasePresenter<IAddCourseView> {

    private static final String TAG = "AddCoursePresenter";
    private CourseManagerModel mModel;

    public AddCoursePresenter(Context context, IAddCourseView iView) {
        super(context, iView);

        mModel = new CourseManagerModel();
    }

    public void addCourse(String courseName, String courseTime){
        SharedPreferences userInfo = mContext.getSharedPreferences("userInfo", 0);
        String teacherId = userInfo.getString("teacherId", null);

        mModel.addCourseInfo(courseName, courseTime, teacherId, new CourseManagerModel.CourseManagerCallback() {
            @Override
            public void querySuccess(List<Course> courseList) {

            }

            @Override
            public void onError(BmobException e) {
                LogUtil.e(TAG, "onError: " + e);
                mIView.onError(e);
            }

            @Override
            public void onSuccess() {
                mIView.onSuccess();
            }
        });
    }
}
