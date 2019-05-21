package com.example.studentmanagersystem.student.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.studentmanagersystem.Utils.LogUtil;
import com.example.studentmanagersystem.base.BasePresenter;
import com.example.studentmanagersystem.entity.Course;
import com.example.studentmanagersystem.student.model.CourseModel;
import com.example.studentmanagersystem.student.presenter.IView.ICourseView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

public class CoursePresenter extends BasePresenter<ICourseView> {

    private static final String TAG = "CoursePresenter";

    private CourseModel mModel;

    public CoursePresenter(Context context, ICourseView iView) {
        super(context, iView);

        mModel = new CourseModel();
        loadChoseCourseInfo();
    }

    /**
     * 加载已选择课程
     */
    public void loadChoseCourseInfo() {
        SharedPreferences userInfo = mContext.getSharedPreferences("userInfo", 0);
        String studentId = userInfo.getString("objectId", null);

        mModel.queryChoseCourseId(studentId, new CourseModel.CourseCallback<List<Course>>() {
            @Override
            public void onSuccess(List<Course> result) {
                mIView.loadCourseInfo(result);
            }

            @Override
            public void onError(Throwable e) {
                mIView.onError(e);
            }
        });

    }
}
