package com.example.studentmanagersystem;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.studentmanagersystem.base.BasePresenter;
import com.example.studentmanagersystem.entity.User;

import cn.bmob.v3.exception.BmobException;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<ILoginView> {

    private static final String TAG = "LoginPresenter";
    private LoginModel mModel;

    public LoginPresenter(Context context, ILoginView iView) {
        super(context, iView);

        mModel = new LoginModel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void login(String userId, String password) {
        mModel.login(userId, password, new LoginModel.LoginCallback() {
            @Override
            public void accountError() {
                mIView.accountError();
            }

            @Override
            public void querySuccess(User user) {
                mIView.loginSuccess(user.getPermission());
                saveCache(user);
            }

            @Override
            public void queryFailed(BmobException e) {
                mIView.loginFailed(e);
            }
        });
    }

    private void saveCache(final User user) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                SharedPreferences userInfo = mContext.getSharedPreferences("userInfo", 0);
                SharedPreferences.Editor editor = userInfo.edit();

                if (user.getPermission() == 0) {
                    editor.putString("teacherId", user.getObjectId());
                } else {
                    editor.putString("studentId", user.getObjectId());
                }
                editor.putString("userId", user.getUserId());
                editor.putString("userName", user.getUserName());
                editor.putString("password", user.getPassword());
                editor.putInt("permission", user.getPermission());

                editor.apply();

                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
