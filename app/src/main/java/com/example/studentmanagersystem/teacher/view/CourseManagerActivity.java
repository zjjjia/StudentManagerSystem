package com.example.studentmanagersystem.teacher.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.teacher.adapter.CourseListAdapter;
import com.example.studentmanagersystem.base.BaseActivity;
import com.example.studentmanagersystem.entity.Course;
import com.example.studentmanagersystem.teacher.presenter.CourseManagerPresenter;
import com.example.studentmanagersystem.teacher.presenter.IView.ICourseManagerView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

public class CourseManagerActivity extends BaseActivity implements ICourseManagerView {

    private Button addCourseBtn;
    private RecyclerView courseRecyclerView;
    private CourseListAdapter mAdapter;
    private CourseManagerPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_manager);

        mPresenter = new CourseManagerPresenter(this, this);
        initView();
    }
    @Override
    public void onRestart(){
        super.onRestart();
        mPresenter.loadCourseInfo();
    }

    private void initView(){
        addCourseBtn = findViewById(R.id.add_course_btn);

        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseManagerActivity.this, AddCourseActivity.class);
                startActivity(intent);
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        courseRecyclerView = findViewById(R.id.course_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        courseRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new CourseListAdapter();
        courseRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void loadCourseInfo(List<Course> courseList) {
        mAdapter.fillList(courseList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadError(BmobException e) {
        Toast.makeText(this, "课表加载失败：" + e, Toast.LENGTH_SHORT).show();
    }
}
