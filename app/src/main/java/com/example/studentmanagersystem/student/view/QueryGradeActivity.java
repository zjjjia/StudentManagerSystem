package com.example.studentmanagersystem.student.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.base.BaseActivity;
import com.example.studentmanagersystem.entity.Course;
import com.example.studentmanagersystem.entity.Grade;
import com.example.studentmanagersystem.student.adapter.GradeListAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class QueryGradeActivity extends BaseActivity {

    private RecyclerView mGradeRecycler;
    private GradeListAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_grade);
        initView();
    }

    private void initView(){
        mGradeRecycler = findViewById(R.id.grade_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mGradeRecycler.setLayoutManager(layoutManager);
        mAdapter = new GradeListAdapter();
        mGradeRecycler.setAdapter(mAdapter);
        loadGradeInfo();
    }

    private void loadGradeInfo(){
        SharedPreferences userInfo = getSharedPreferences("userInfo", 0);
        String studentId = userInfo.getString("studentId", null);
        BmobQuery<Grade> query = new BmobQuery<>();
        query.addWhereEqualTo("studentId", studentId);
        query.findObjects(new FindListener<Grade>() {
            @Override
            public void done(List<Grade> list, BmobException e) {
                if(e == null){
                    loadCourseName(list);
                }
            }
        });
    }

    private void loadCourseName(final List<Grade> gradeList){
        List<String> courseIds = new ArrayList<>();
        for(Grade grade : gradeList){
            courseIds.add(grade.getCourseId());
        }
        BmobQuery<Course> query = new BmobQuery<>();
        query.addQueryKeys("courseName");
        query.addWhereContainedIn("objectId", courseIds);
        query.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> list, BmobException e) {
                if(e == null){
                    for(Grade grade : gradeList){
                        for(Course course : list){
                            if(grade.getCourseId().equals(course.getObjectId())){
                                grade.setCourseName(course.getCourseName());
                            }
                        }
                    }
                    mAdapter.fillList(gradeList);
                    mAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(QueryGradeActivity.this, "成绩查询失败" + e, Toast.LENGTH_SHORT).show();
                    QueryGradeActivity.this.finish();
                }
            }
        });
    }
}
