package com.example.studentmanagersystem.teacher.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.studentmanagersystem.Utils.LogUtil;
import com.example.studentmanagersystem.base.BasePresenter;
import com.example.studentmanagersystem.callback.Callback;
import com.example.studentmanagersystem.entity.ChoseCourse;
import com.example.studentmanagersystem.entity.Course;
import com.example.studentmanagersystem.teacher.model.GradeManagerModel;
import com.example.studentmanagersystem.teacher.presenter.IView.IGradeManagerView;

import java.util.ArrayList;
import java.util.List;

public class GradeManagerPresenter extends BasePresenter<IGradeManagerView> {

    private static final String TAG = "GradeManagerPresenter";
    private GradeManagerModel mModel;
    private List<Course> mCourses = new ArrayList<>();

    public GradeManagerPresenter(Context context, IGradeManagerView iView) {
        super(context, iView);

        mModel = new GradeManagerModel();
        loadSelfCourseInfo();
    }

    /**
     * 加载自己的课程列表
     */
    private void loadSelfCourseInfo() {
        SharedPreferences userInfo = mContext.getSharedPreferences("userInfo", 0);
        String teacherId = userInfo.getString("teacherId", null);
        mModel.querySelfCourseList(teacherId, new Callback<List<Course>>() {
            @Override
            public void onSuccess(List<Course> result) {
                LogUtil.d(TAG, "onSuccess: " + result.toString());
                mCourses = result;
                mIView.loadCourseSuccess(result);
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "onError: " + e);
                mIView.onError(e);
            }
        });
    }

    /**
     * 加载这门课程的学生信息
     */
    public void loadCourseStudentInfo(String courseName) {
        if (!mCourses.isEmpty()) {
            for (Course course : mCourses) {
                if (course.getCourseName().equals(courseName)) {
                    String courseId = course.getObjectId();
                    mModel.queryStudentInfoByCourseId(courseId, new Callback<List<ChoseCourse>>() {
                        @Override
                        public void onSuccess(List<ChoseCourse> result) {
                            mIView.loadGradeEditInfo(result);
                        }

                        @Override
                        public void onError(Throwable e) {
                            mIView.onError(e);
                        }
                    });
                    break;
                }
            }
        }
    }

    public void saveGradeInfo(List<ChoseCourse> gradeInfo){
        mModel.saveGradeInfo(gradeInfo, new Callback() {
            @Override
            public void onSuccess(Object result) {
                mIView.saveGradeSuccess();
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "onError: " + e);
                mIView.onError(e);
            }
        });
    }
}
