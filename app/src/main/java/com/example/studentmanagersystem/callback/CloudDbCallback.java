package com.example.studentmanagersystem.callback;

import cn.bmob.v3.exception.BmobException;

public interface CloudDbCallback<T> {

    void accountError();

    void querySuccess(T t);

    void queryFailed(BmobException e);
}
