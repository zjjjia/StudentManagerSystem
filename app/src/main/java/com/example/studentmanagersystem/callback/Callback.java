package com.example.studentmanagersystem.callback;


public interface Callback<T> {
    void onSuccess(T result);

    void onError(Throwable e);
}
