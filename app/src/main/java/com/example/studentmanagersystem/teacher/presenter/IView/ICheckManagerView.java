package com.example.studentmanagersystem.teacher.presenter.IView;

import com.example.studentmanagersystem.base.IView;
import com.example.studentmanagersystem.entity.Check;
import com.example.studentmanagersystem.entity.User;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

public interface ICheckManagerView extends IView {

    void onSuccess();

    void onError(BmobException e);

    void onError(Throwable e);

    void loadCheckKeyInfo(String checkKey);

    void startOnSuccess();

    void loadCheckedInfo(List<Check> checkList);

    void loadUnCheckedInfo(List<User> unCheckList);
}
