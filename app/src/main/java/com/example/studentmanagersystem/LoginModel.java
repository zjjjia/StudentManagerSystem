package com.example.studentmanagersystem;

import com.example.studentmanagersystem.entity.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginModel {

    public void login(String userId, String password, final LoginCallback callback){

        BmobQuery<User> query = new BmobQuery<>();

        query.addWhereEqualTo("userId", userId);
        query.addWhereEqualTo("password", password);

        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if(e == null) {
                    if(list.size() == 0){
                        callback.accountError();
                    }else {
                        callback.querySuccess(list.get(0));
                    }
                }else{
                    callback.queryFailed(e);
                }
            }
        });
    }

    public interface LoginCallback{
        void accountError();

        void querySuccess(User user);

        void queryFailed(BmobException e);
    }
}
