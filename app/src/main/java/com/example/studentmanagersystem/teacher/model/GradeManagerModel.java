package com.example.studentmanagersystem.teacher.model;


import com.example.studentmanagersystem.Utils.LogUtil;
import com.example.studentmanagersystem.callback.Callback;
import com.example.studentmanagersystem.entity.ChoseCourse;
import com.example.studentmanagersystem.entity.Course;
import com.example.studentmanagersystem.entity.Grade;
import com.example.studentmanagersystem.entity.User;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GradeManagerModel {

    private static final String TAG = "GradeManagerModel";

    /**
     * 查询自己的课程列表
     */
    public void querySelfCourseList(String teacherId, final Callback<List<Course>> callback) {
        BmobQuery<Course> query = new BmobQuery<>();
        query.addWhereEqualTo("teacherId", teacherId);
        query.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> list, BmobException e) {
                if (e == null) {
                    callback.onSuccess(list);
                } else {
                    callback.onError(e);
                }
            }
        });
    }

    /**
     * 根据CourseId查询该课程的学生信息
     */
    public void queryStudentInfoByCourseId(String courseId, final Callback<List<ChoseCourse>> callback) {
        BmobQuery<ChoseCourse> query = new BmobQuery<>();
        query.addWhereEqualTo("courseId", courseId);
        query.findObjects(new FindListener<ChoseCourse>() {
            @Override
            public void done(List<ChoseCourse> list, BmobException e) {
                if (e == null) {
                    queryUserInfoByStudentId(list, callback);
                } else {
                    callback.onError(e);
                }
            }
        });
    }

    public void saveGradeInfo(final List<ChoseCourse> gradeList, final Callback callback){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(final ObservableEmitter<Object> emitter) throws Exception {
                for(ChoseCourse gradeInfo : gradeList){
                    LogUtil.d(TAG, "subscribe: save");
                    Grade grade = new Grade();
                    grade.setStudentId(gradeInfo.getStudentId());
                    grade.setCourseId(gradeInfo.getCourseId());
                    grade.setGrade(gradeInfo.getGrade());
                    grade.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e != null){
                               emitter.onError(e);
                            }
                        }
                    });
                }
                emitter.onNext(null);
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        callback.onSuccess(null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 根据studentId查询学生的姓名学号等
     */
    private void queryUserInfoByStudentId(final List<ChoseCourse> choseCourses, final Callback<List<ChoseCourse>> callback) {
        List<String> studentId = new ArrayList<>();
        for (ChoseCourse course : choseCourses) {
            studentId.add(course.getStudentId());
        }
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereContainedIn("objectId", studentId);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e == null) {
                    for (ChoseCourse course : choseCourses) {
                        for (User user : list) {
                            if (course.getStudentId().equals(user.getObjectId())) {
                                course.setStudentName(user.getUserName());
                                course.setUserId(user.getUserId());
                            }
                        }
                    }
                    callback.onSuccess(choseCourses);
                } else {
                    callback.onError(e);
                }
            }
        });
    }
}
