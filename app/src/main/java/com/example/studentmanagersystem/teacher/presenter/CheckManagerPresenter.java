package com.example.studentmanagersystem.teacher.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.studentmanagersystem.teacher.model.CheckManagerModel;
import com.example.studentmanagersystem.Utils.LogUtil;
import com.example.studentmanagersystem.base.BasePresenter;
import com.example.studentmanagersystem.entity.Check;
import com.example.studentmanagersystem.entity.SetCheck;
import com.example.studentmanagersystem.entity.User;
import com.example.studentmanagersystem.teacher.presenter.IView.ICheckManagerView;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.bmob.v3.exception.BmobException;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class CheckManagerPresenter extends BasePresenter<ICheckManagerView> {

    private static final String TAG = "CheckManagerPresenter";
    private CheckManagerModel mModel;
    private String mTeacherId;
    private String mCheckKey;

    public CheckManagerPresenter(Context context, ICheckManagerView iView) {
        super(context, iView);

        SharedPreferences userInfo = mContext.getSharedPreferences("userInfo", 0);
        mTeacherId = userInfo.getString("teacherId", null);
        mModel = new CheckManagerModel();
        createCheck();
    }

    /**
     * 创建签到任务
     */
    private void createCheck() {
        mModel.createCheck(mTeacherId, new CheckManagerModel.CheckManagerCallback() {
            @Override
            public void onSuccess() {
                mIView.onSuccess();
                loadCheckKeyInfo();
            }

            @Override
            public void onError(BmobException e) {
                mIView.onError(e);
            }

            @Override
            public void querySuccessCallback(List list) {

            }
        });
    }

    /**
     * 加载签到口令
     */
    private void loadCheckKeyInfo() {
        mModel.getCheckKey(mTeacherId, new CheckManagerModel.CheckManagerCallback<SetCheck>() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(BmobException e) {
                mIView.onError(e);
            }

            @Override
            public void querySuccessCallback(List<SetCheck> list) {
                mCheckKey = list.get(list.size() - 1).getObjectId();
                mIView.loadCheckKeyInfo(mCheckKey);
            }
        });
    }

    /**
     * 开始签到
     */
    public void startChecked() {
        long startTime = Calendar.getInstance().getTimeInMillis();
        mModel.startCheck(mCheckKey, startTime, new CheckManagerModel.CheckManagerCallback() {
            @Override
            public void onSuccess() {
                mIView.startOnSuccess();
                intervalLoadInfo();
            }

            @Override
            public void onError(BmobException e) {
                mIView.onError(e);
            }

            @Override
            public void querySuccessCallback(List list) {

            }
        });
    }

    /**
     * 定时器：每隔1秒更新签到学生信息
     * */
    private void intervalLoadInfo(){
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogUtil.d(TAG, "onNext: intervalLoadInfo");
                        loadCheckedStudentInfo();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "onError: " + e);
                        mIView.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 加载已签到学生信息
     */
    private void loadCheckedStudentInfo() {
        mModel.loadCheckedInfo(mCheckKey, new CheckManagerModel.CheckManagerCallback<Check>() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(BmobException e) {
                mIView.onError(e);
            }

            @Override
            public void querySuccessCallback(List<Check> list) {
                mIView.loadCheckedInfo(list);
                loadUnCheckedStudentInfo(list);
            }

        });
    }

    /**
     * 加载未签到学生信息
     *
     * @param checkedList 已签到学生信息
     */
    private void loadUnCheckedStudentInfo(List<Check> checkedList) {
        mModel.loadUnCheckedInfo(checkedList, new CheckManagerModel.CheckManagerCallback<User>() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(BmobException e) {
                mIView.onError(e);
            }

            @Override
            public void querySuccessCallback(List<User> list) {
                mIView.loadUnCheckedInfo(list);
            }
        });
    }

}
