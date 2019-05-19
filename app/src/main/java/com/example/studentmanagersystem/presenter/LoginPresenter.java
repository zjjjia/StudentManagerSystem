package com.example.studentmanagersystem.presenter;

import android.content.Context;

import com.example.studentmanagersystem.Model.LoginModel;
import com.example.studentmanagersystem.base.BasePresenter;
import com.example.studentmanagersystem.bmboTable.User;
import com.example.studentmanagersystem.presenter.iview.ILoginView;

import cn.bmob.v3.exception.BmobException;

public class LoginPresenter extends BasePresenter<ILoginView> {

    private static final String TAG = "LoginPresenter";
    private LoginModel mModel;

    public LoginPresenter(Context context, ILoginView iView) {
        super(context, iView);

        mModel = new LoginModel();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void login(String userId, String password){
        mModel.login(userId, password, new LoginModel.LoginCallback() {
            @Override
            public void accountError() {
                mIView.accountError();
            }

            @Override
            public void querySuccess(User user) {
                mIView.loginSuccess(user.getPermission());
            }

            @Override
            public void queryFailed(BmobException e) {
                mIView.loginFailed(e);
            }
        });
    }
}
