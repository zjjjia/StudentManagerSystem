package com.example.studentmanagersystem.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.studentmanagersystem.Utils.LogUtil;
import com.example.studentmanagersystem.entity.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class BaseActivity extends AppCompatActivity implements IView{

    private static final String TAG = "BaseActivity";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Bmob.initialize(this, "78b9431d6307f6cb76267edac524790f");

        //initApp();
    }

    private void initApp(){
        User user  = new User();

        user.setUserId(20153027);
        user.setUserName("test");
        user.setPassword("test");
        user.setPermission(0);

        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    LogUtil.d(TAG, "done: save success!");
                    Toast.makeText(getBaseContext(), "初始化成功", Toast.LENGTH_SHORT).show();
                }else{
                    LogUtil.e(TAG, "done: " + e);
                    Toast.makeText(getBaseContext(), "初始化失败", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

}
