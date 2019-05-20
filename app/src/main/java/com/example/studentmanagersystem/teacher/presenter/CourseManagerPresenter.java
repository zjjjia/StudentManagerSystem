package com.example.studentmanagersystem.teacher.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.studentmanagersystem.teacher.model.CourseManagerModel;
import com.example.studentmanagersystem.base.BasePresenter;
import com.example.studentmanagersystem.entity.Course;
import com.example.studentmanagersystem.teacher.presenter.IView.ICourseManagerView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

public class CourseManagerPresenter extends BasePresenter<ICourseManagerView> {

    private static final String TAG = "CourseManagerPresenter";
    private CourseManagerModel mModel;

    public CourseManagerPresenter(Context context, ICourseManagerView iView) {
        super(context, iView);
        mModel = new CourseManagerModel();

        loadCourseInfo();
    }

    public void loadCourseInfo(){
        SharedPreferences userInfo = mContext.getSharedPreferences("userInfo", 0);
        String teacherId = userInfo.getString("teacherId", null);
        mModel.loadCourseInfo(teacherId, new CourseManagerModel.CourseManagerCallback() {
            @Override
            public void querySuccess(List<Course> courseList) {
                mIView.loadCourseInfo(courseList);
            }

            @Override
            public void onError(BmobException e) {
                Log.e(TAG, "onError: " + e);
                mIView.loadError(e);
            }

            @Override
            public void onSuccess() {

            }
        });
    }


}
