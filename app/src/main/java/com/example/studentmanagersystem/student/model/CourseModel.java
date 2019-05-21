package com.example.studentmanagersystem.student.model;

import com.example.studentmanagersystem.Utils.LogUtil;
import com.example.studentmanagersystem.entity.ChoseCourse;
import com.example.studentmanagersystem.entity.Course;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CourseModel {

    private static final String TAG = "CourseModel";

    public void queryStudentCourseInfo(String studentId, final CourseCallback<List<Course>> callback) {
        BmobQuery<Course> query = new BmobQuery<>();
        query.addWhereEqualTo("studentId", studentId);
        query.include("Course[courseName],User[userName]");
        query.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> list, BmobException e) {
                if (e == null) {
                    LogUtil.d(TAG, "done: " + list.toString());
                    callback.onSuccess(list);
                } else {
                    callback.onError(e);
                }
            }
        });
    }

    public void queryAllCourseInfo(final CourseCallback<List<Course>> callback) {
        BmobQuery<Course> query = new BmobQuery<>();
        query.include("User[userName]");
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

    public void saveChoseCourseInfo(final List<String> choseCourseIdInfo, final String studentId,
                                    final CourseCallback callback){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(final ObservableEmitter<Object> emitter) throws Exception {
                for(String courseId : choseCourseIdInfo){
                    ChoseCourse choseCourse = new ChoseCourse();
                    choseCourse.setCourseId(courseId);
                    choseCourse.setStudentId(studentId);
                    choseCourse.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e != null){
                                emitter.onError(e);
                                emitter.onComplete();
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

    public interface CourseCallback<T> {
        void onSuccess(T result);

        void onError(Throwable e);
    }
}
