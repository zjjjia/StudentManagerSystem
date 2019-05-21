package com.example.studentmanagersystem.student.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.studentmanagersystem.base.BasePresenter;
import com.example.studentmanagersystem.entity.Course;
import com.example.studentmanagersystem.student.model.CourseModel;
import com.example.studentmanagersystem.student.presenter.IView.IChooseCourseView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

public class ChooseCoursePresenter extends BasePresenter<IChooseCourseView> {

    private static final String TAG = "ChooseCoursePresenter";
    private CourseModel mModel;

    public ChooseCoursePresenter(Context context, IChooseCourseView iView) {
        super(context, iView);

        mModel = new CourseModel();
        loadAllCourseInfo();
    }

    /**
     * 加载所有课程的信息
     */
    private void loadAllCourseInfo() {
        mModel.queryAllCourseInfo(new CourseModel.CourseCallback<List<Course>>() {
            @Override
            public void onSuccess(List<Course> result) {
                mIView.loadCourseSuccess(result);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e);
                mIView.onError(e);
            }
        });
    }

    public void saveChoosedCourseInfo(List<String> courseIdInfo){
        SharedPreferences userInfo = mContext.getSharedPreferences("userInfo", 0);
        String studentId = userInfo.getString("studentId", null);
        mModel.saveChoseCourseInfo(courseIdInfo, studentId, new CourseModel.CourseCallback() {
            @Override
            public void onSuccess(Object result) {
                mIView.choseSuccess();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e);
                mIView.onError(e);
            }
        });
    }
}
