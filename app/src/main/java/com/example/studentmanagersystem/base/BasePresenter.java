package com.example.studentmanagersystem.base;

import android.content.Context;

public abstract class BasePresenter<T extends IView>{

    public Context mContext;
    public T mIView;

    public BasePresenter(Context context, T iView){
        mContext = context;
        mIView = iView;
    }

    public void onDestroy(){
        mContext = null;
        mIView = null;
    }
}
