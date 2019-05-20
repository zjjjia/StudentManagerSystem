package com.example.studentmanagersystem.student.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.base.BaseActivity;

public class StudentHomeActivity extends BaseActivity {

    private Button courseBtn;
    private Button checkBtn;
    private Button gradeBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        initView();
    }

    private void initView(){
        courseBtn = findViewById(R.id.course_manager_btn);
        checkBtn = findViewById(R.id.to_check_btn);
        gradeBtn = findViewById(R.id.query_grade_btn);

        courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        gradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}