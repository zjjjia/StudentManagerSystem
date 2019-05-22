package com.example.studentmanagersystem.student.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.base.BaseActivity;
import com.example.studentmanagersystem.student.presenter.CheckPresenter;
import com.example.studentmanagersystem.student.presenter.IView.ICheckView;

public class CheckActivity extends BaseActivity implements ICheckView {

    private EditText mCheckKeyEdit;
    private Button mConfirmBtn;
    private CheckPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        mPresenter = new CheckPresenter(this, this);
        initView();
    }

    private void initView(){
        mCheckKeyEdit = findViewById(R.id.check_key_edit);
        mConfirmBtn = findViewById(R.id.confirm_btn);

        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkKey = mCheckKeyEdit.getText().toString();
                mPresenter.requestCheck(checkKey);
            }
        });
    }

    @Override
    public void checkTimeOut() {
        Toast.makeText(this, "已超过签到时间", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void checkSuccess() {
        Toast.makeText(this, "签到成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(this, "error: " + e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkWrong() {
        Toast.makeText(this, "签到口令错误", Toast.LENGTH_SHORT).show();
    }
}
