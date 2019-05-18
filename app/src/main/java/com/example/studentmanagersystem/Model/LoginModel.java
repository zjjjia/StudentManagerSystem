package com.example.studentmanagersystem.Model;

import com.example.studentmanagersystem.callback.CloudDbCallback;
import com.example.studentmanagersystem.entity.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginModel {

    public void login(String userId, String password, final CloudDbCallback<User> cloudDbCallback){

        BmobQuery<User> query = new BmobQuery<>();

        query.addWhereEqualTo("userId", Integer.valueOf(userId));
        query.addWhereEqualTo("password", password);

        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if(e == null) {
                    if(list.size() == 0){
                        cloudDbCallback.accountError();
                    }else {
                        cloudDbCallback.querySuccess(list.get(0));
                    }
                }else{
                    cloudDbCallback.queryFailed(e);
                }
            }
        });
    }
}
