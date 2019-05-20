package com.example.studentmanagersystem.teacher.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.base.BaseActivity;

/**
 * 教师端首页的Activity
 */
public class TeacherHomeActivity extends BaseActivity {

    private Button mCourseBtn;
    private Button mCheckBtn;
    private Button mGradeBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        initView();
    }

    private void initView() {
        mCourseBtn = findViewById(R.id.manager_course);
        mCheckBtn = findViewById(R.id.manager_check);
        mGradeBtn = findViewById(R.id.manager_grade);

        //进入课程管理
        mCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherHomeActivity.this, CourseManagerActivity.class);
                startActivity(intent);
            }
        });

        //进入签到管理
        mCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherHomeActivity.this, CheckManagerActivity.class);
                startActivity(intent);
            }
        });

        //进入成绩管理
        mGradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherHomeActivity.this, GradeManagerActivity.class);
                startActivity(intent);
            }
        });
    }
}
