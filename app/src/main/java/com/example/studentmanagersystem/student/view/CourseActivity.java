package com.example.studentmanagersystem.student.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.base.BaseActivity;
import com.example.studentmanagersystem.entity.Course;
import com.example.studentmanagersystem.student.adapter.CourseListAdapter;
import com.example.studentmanagersystem.student.presenter.CoursePresenter;
import com.example.studentmanagersystem.student.presenter.IView.ICourseView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

public class CourseActivity extends BaseActivity implements ICourseView {

    private RecyclerView mCourseRecycler;
    private Button mAddCourseBtn;
    private CourseListAdapter mAdapter;
    private CoursePresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        mPresenter = new CoursePresenter(this, this);
        initView();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        mPresenter.loadCourseInfo();
    }

    private void initView(){
        mCourseRecycler = findViewById(R.id.course_list);
        mAddCourseBtn = findViewById(R.id.add_course_btn);

        mAddCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mCourseRecycler.setLayoutManager(layoutManager);
        mAdapter = new CourseListAdapter();
        mCourseRecycler.setAdapter(mAdapter);
    }

    @Override
    public void loadCourseInfo(List<Course> courseList) {

    }

    @Override
    public void onError(BmobException e) {

    }
}
