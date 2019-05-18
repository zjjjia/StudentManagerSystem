package com.example.studentmanagersystem.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.base.BaseActivity;
import com.example.studentmanagersystem.presenter.LoginPresenter;
import com.example.studentmanagersystem.presenter.iview.ILoginView;
import com.example.studentmanagersystem.view.teacher.TeacherHomeActivity;

import cn.bmob.v3.exception.BmobException;

public class LoginActivity extends BaseActivity implements ILoginView {

    private EditText userIdText;
    private EditText passwordText;
    private Button loginBtn;

    private LoginPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenter(this, this);
        initView();
    }

    private void initView(){
        userIdText = findViewById(R.id.user_id);
        passwordText = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login(){
        String userId = userIdText.getText().toString();
        String password = passwordText.getText().toString();

        if(userId.isEmpty() || userId.equals(" ")){
            Toast.makeText(this, "学号/工号不能为空", Toast.LENGTH_SHORT).show();
        }else if(password.isEmpty() || password.equals(" ")){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
        }else {
            mPresenter.login(userId, password);
        }
    }

    @Override
    public void loginFailed(BmobException e) {
        Toast.makeText(this, "登录失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(int permission) {
        if(permission == 0){         //进入教师端
            Intent intent = new Intent(this, TeacherHomeActivity.class);
            startActivity(intent);
        }else{                     //进入学生端

        }
        finish();
    }

    @Override
    public void accountError() {
        Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
    }
}
