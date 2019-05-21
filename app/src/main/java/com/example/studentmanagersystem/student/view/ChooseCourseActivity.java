package com.example.studentmanagersystem.student.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.entity.Course;
import com.example.studentmanagersystem.student.adapter.ChooseCourseAdapter;
import com.example.studentmanagersystem.student.presenter.ChooseCoursePresenter;
import com.example.studentmanagersystem.student.presenter.IView.IChooseCourseView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

public class ChooseCourseActivity extends AppCompatActivity implements IChooseCourseView {

    private RecyclerView mChooseCourseRecycler;
    private Button completedChooseBtn;
    private ChooseCourseAdapter mAdapter;
    private ChooseCoursePresenter mPrenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_course);

        mPrenter = new ChooseCoursePresenter(this, this);
        initView();
    }

    private void initView() {
        mChooseCourseRecycler = findViewById(R.id.choose_course_list);
        completedChooseBtn = findViewById(R.id.completed_choose_btn);

        completedChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setChooseCourseListener(new ChooseCourseAdapter.ChooseCourseListener() {
                    @Override
                    public void onChooseListener(List<String> choseList) {
                        mPrenter.saveChoosedCourseInfo(choseList);
                    }
                });
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mChooseCourseRecycler.setLayoutManager(layoutManager);
        mAdapter = new ChooseCourseAdapter();
        mChooseCourseRecycler.setAdapter(mAdapter);

    }

    @Override
    public void loadCourseSuccess(List<Course> courseList) {
        mAdapter.fillList(courseList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(this, "error: " + e, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void choseSuccess() {
        Toast.makeText(this, "选课成功", Toast.LENGTH_SHORT).show();
    }
}
