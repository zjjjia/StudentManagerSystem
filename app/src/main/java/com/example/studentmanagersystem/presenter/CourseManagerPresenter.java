package com.example.studentmanagersystem.presenter;

import android.content.Context;
import android.util.Log;

import com.example.studentmanagersystem.Model.CourseManagerModel;
import com.example.studentmanagersystem.base.BasePresenter;
import com.example.studentmanagersystem.bmboTable.Course;
import com.example.studentmanagersystem.presenter.iview.ICourseManagerView;

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

    private void loadCourseInfo(){
        String userId = "20153027";
        mModel.loadCourseInfo(Integer.valueOf(userId), new CourseManagerModel.CourseManagerCallback() {
            @Override
            public void querySuccess(List<Course> courseList) {
                mIView.loadCourseInfo(courseList);
            }

            @Override
            public void queryFailed(BmobException e) {
                Log.e(TAG, "queryFailed: " + e);
                mIView.loadError(e);
            }
        });
    }
}
