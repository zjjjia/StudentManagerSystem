package com.example.studentmanagersystem.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.studentmanagersystem.Utils.LogUtil;
import com.example.studentmanagersystem.entity.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


public class BaseActivity extends FragmentActivity implements IView{

    private static final String TAG = "BaseActivity";
    private List<User> initAccountList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Bmob.initialize(this, "78b9431d6307f6cb76267edac524790f");

        query();
    }

    private void query(){
        BmobQuery<User> query = new BmobQuery<>();
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if(e == null){
                    if(list.size() == 0){
                        initApp();
                    }
                }else{
                    LogUtil.e(TAG, "done: " + e);
                    Toast.makeText(BaseActivity.this, "error： " + e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initApp(){
        addAccount("20153027", "用户1", "test",  0);
        addAccount("201521093026", "用户2", "test", 1);
        addAccount("201521093027", "用户3", "test", 1);
        addAccount("201521093028", "用户4", "test", 1);

        for(User user : initAccountList){
            user.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if(e != null){
                        LogUtil.e(TAG, "done: " + e);
                        Toast.makeText(BaseActivity.this, "error: " + e, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void addAccount(String userId, String userName, String password, Integer permission){
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        user.setPermission(permission);

        initAccountList.add(user);
    }

}
