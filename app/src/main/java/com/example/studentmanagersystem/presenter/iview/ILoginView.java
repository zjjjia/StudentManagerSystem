package com.example.studentmanagersystem.presenter.iview;

import com.example.studentmanagersystem.base.IView;

import cn.bmob.v3.exception.BmobException;

public interface ILoginView extends IView {

    void loginFailed(BmobException e);

    void loginSuccess(int permission);

    void accountError();
}
