package com.example.studentmanagersystem.student.model;

import com.example.studentmanagersystem.entity.ChoseCourse;
import com.example.studentmanagersystem.entity.Course;
import com.example.studentmanagersystem.entity.User;

import java.util.ArrayList;
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

    public void queryAllCourseInfo(final CourseCallback<List<Course>> callback) {
        BmobQuery<Course> query = new BmobQuery<>();

        query.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> list, BmobException e) {
                if (e == null) {
                    queryTeacherInfoByTeacherId(list, callback);
                } else {
                    callback.onError(e);
                }
            }
        });
    }

    public void saveChoseCourseInfo(final List<String> choseCourseIdInfo, final String studentId,
                                    final CourseCallback callback) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(final ObservableEmitter<Object> emitter) throws Exception {
                for (String courseId : choseCourseIdInfo) {
                    ChoseCourse choseCourse = new ChoseCourse();
                    choseCourse.setCourseId(courseId);
                    choseCourse.setStudentId(studentId);
                    choseCourse.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e != null) {
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

    /**
     * 查询ChoseCourse表中已选课程的courseId
     */
    public void queryChoseCourseId(String studentId, final CourseCallback<List<Course>> callback) {
        final BmobQuery<ChoseCourse> query = new BmobQuery<>();
        query.addWhereEqualTo("studentId", studentId);
        query.addQueryKeys("courseId");
        query.findObjects(new FindListener<ChoseCourse>() {
            @Override
            public void done(List<ChoseCourse> list, BmobException e) {
                if (e == null) {
                    queryCourseInfoByCourseId(list, callback);
                }
            }
        });
    }

    /**
     * 根据courseId查询Course表中课程的其他信息
     */
    private void queryCourseInfoByCourseId(List<ChoseCourse> list, final CourseCallback<List<Course>> callback) {
        BmobQuery<Course> query = new BmobQuery<>();
        List<String> courseIdList = new ArrayList<>();
        for (ChoseCourse course : list) {
            courseIdList.add(course.getCourseId());
        }
        query.addWhereContainedIn("objectId", courseIdList);
        query.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> list, BmobException e) {
                if (e == null) {
                    queryTeacherInfoByTeacherId(list, callback);
                } else {
                    callback.onError(e);
                }
            }
        });
    }

    /**
     * 根据Course表中的teacherId查询对应课程老师的姓名
     */
    private void queryTeacherInfoByTeacherId(final List<Course> courseList, final CourseCallback<List<Course>> callback) {
        List<String> teacherIds = new ArrayList<>();
        for (final Course course : courseList) {
            teacherIds.add(course.getTeacherId());
        }
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereContainedIn("objectId", teacherIds);
        query.addQueryKeys("userName");
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if(e == null){
                    for(Course course : courseList){
                        for(User user : list){
                            if(course.getTeacherId().equals(user.getObjectId())){
                                course.setUserName(user.getUserName());
                            }
                        }
                    }
                }
                callback.onSuccess(courseList);
            }
        });
    }

    public interface CourseCallback<T> {
        void onSuccess(T result);

        void onError(Throwable e);
    }
}
