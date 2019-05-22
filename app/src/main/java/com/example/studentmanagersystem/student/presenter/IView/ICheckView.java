package com.example.studentmanagersystem.student.presenter.IView;

import com.example.studentmanagersystem.base.IView;

public interface ICheckView extends IView {

    void checkTimeOut();

    void checkSuccess();

    void onError(Throwable e);

    void checkWrong();
}
