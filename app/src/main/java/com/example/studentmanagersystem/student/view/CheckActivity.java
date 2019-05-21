package com.example.studentmanagersystem.student.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.base.BaseActivity;

public class CheckActivity extends BaseActivity {

    private EditText mCheckKeyEdit;
    private Button mConfirmBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        initView();
    }

    private void initView(){
        mCheckKeyEdit = findViewById(R.id.check_key_edit);
        mConfirmBtn = findViewById(R.id.confirm_btn);

        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
