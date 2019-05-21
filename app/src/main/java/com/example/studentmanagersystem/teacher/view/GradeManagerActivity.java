package com.example.studentmanagersystem.teacher.view;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.Utils.LogUtil;
import com.example.studentmanagersystem.base.BaseActivity;
import com.example.studentmanagersystem.entity.ChoseCourse;
import com.example.studentmanagersystem.entity.Course;
import com.example.studentmanagersystem.teacher.adapter.EditGradeListAdapter;
import com.example.studentmanagersystem.teacher.presenter.GradeManagerPresenter;
import com.example.studentmanagersystem.teacher.presenter.IView.IGradeManagerView;

import java.util.ArrayList;
import java.util.List;

public class GradeManagerActivity extends BaseActivity implements IGradeManagerView {

    private static final String TAG = "GradeManagerActivity";
    private Spinner mChooseSpinner;
    private ArrayAdapter<String> mSpinnerAdapter;
    private RecyclerView mEditGradeRecycler;
    private EditGradeListAdapter mEditGradeAdapter;
    private Button mEditSubmitBtn;
    private GradeManagerPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_manager);

        mPresenter = new GradeManagerPresenter(this, this);
        initView();
    }

    private void initView() {
        mEditGradeRecycler = findViewById(R.id.edit_grade_recycler);
        mEditSubmitBtn = findViewById(R.id.submit_btn);
        mChooseSpinner = findViewById(R.id.choose_course_spinner);

        mChooseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String courseName = parent.getItemAtPosition(position).toString();
                mPresenter.loadCourseStudentInfo(courseName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mEditSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditGradeAdapter.setEditGradeListener(new EditGradeListAdapter.EditGradeListListener() {
                    @Override
                    public void getEditResultListener(List<ChoseCourse> result) {
                        LogUtil.d(TAG, "getEditResultListener: " + result.toString());
                        mPresenter.saveGradeInfo(result);
                    }
                });
                mEditGradeAdapter.loadCourseGradeInfo();
            }
        });
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mEditGradeRecycler.setLayoutManager(layoutManager);
        mEditGradeAdapter = new EditGradeListAdapter();
        mEditGradeRecycler.setAdapter(mEditGradeAdapter);
    }


    @Override
    public void loadCourseSuccess(List<Course> list) {
        List<String> courseNames = new ArrayList<>();
        for (Course course : list) {
            courseNames.add(course.getCourseName());
        }
        mSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseNames);
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mChooseSpinner.setAdapter(mSpinnerAdapter);
    }

    @Override
    public void loadGradeEditInfo(List<ChoseCourse> list) {
        mEditGradeAdapter.fillLst(list);
        mEditGradeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(this, "error: " + e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveGradeSuccess() {
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
    }
}