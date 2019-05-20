package com.example.studentmanagersystem.teacher.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.base.BaseActivity;
import com.example.studentmanagersystem.teacher.presenter.AddCoursePresenter;
import com.example.studentmanagersystem.teacher.presenter.IView.IAddCourseView;

import cn.bmob.v3.exception.BmobException;

public class AddCourseActivity extends BaseActivity implements IAddCourseView {

    private EditText mCourseNameEdit;
    private EditText mCourseTimeEdit;
    private Button mCompleteBtn;
    private AddCoursePresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        mPresenter = new AddCoursePresenter(this, this);
        initView();
    }

    private void initView(){
        mCourseNameEdit = findViewById(R.id.edit_course_name);
        mCourseTimeEdit = findViewById(R.id.edit_course_time);
        mCompleteBtn = findViewById(R.id.completed_btn);

        mCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseName = mCourseNameEdit.getText().toString();
                String courseTime = mCourseTimeEdit.getText().toString();

                mPresenter.addCourse(courseName, courseTime);
            }
        });

    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onError(BmobException e) {
        Toast.makeText(this, "添加失败：" + e, Toast.LENGTH_SHORT).show();
    }
}
