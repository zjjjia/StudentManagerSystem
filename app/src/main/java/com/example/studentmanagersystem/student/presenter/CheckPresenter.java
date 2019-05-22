package com.example.studentmanagersystem.student.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.studentmanagersystem.Utils.LogUtil;
import com.example.studentmanagersystem.base.BasePresenter;
import com.example.studentmanagersystem.student.model.CheckModel;
import com.example.studentmanagersystem.student.presenter.IView.ICheckView;

public class CheckPresenter extends BasePresenter<ICheckView> {

    private static final String TAG = "CheckPresenter";
    private CheckModel mModel;

    public CheckPresenter(Context context, ICheckView iView) {
        super(context, iView);

        mModel = new CheckModel();
    }

    public void requestCheck(String checkKey){
        SharedPreferences userInfo = mContext.getSharedPreferences("userInfo", 0);
        String studentId = userInfo.getString("studentId", null);

        mModel.checkRequest(checkKey, studentId, new CheckModel.CheckCallback() {
            @Override
            public void checkTimeOut() {
                mIView.checkTimeOut();
            }

            @Override
            public void keyNotExist() {
                mIView.checkWrong();
            }

            @Override
            public void onSuccess(Object result) {
                mIView.checkSuccess();
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "onError: " + e);
                mIView.onError(e);
            }
        });
    }
}
