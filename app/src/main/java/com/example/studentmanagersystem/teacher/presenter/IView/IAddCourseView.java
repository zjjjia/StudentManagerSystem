package com.example.studentmanagersystem.teacher.presenter.IView;


import com.example.studentmanagersystem.base.IView;

import cn.bmob.v3.exception.BmobException;

public interface IAddCourseView extends IView {

    void onSuccess();

    void onError(BmobException e);
}
