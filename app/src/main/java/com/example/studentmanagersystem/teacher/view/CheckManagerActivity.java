package com.example.studentmanagersystem.teacher.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.teacher.adapter.CheckStudentListAdapter;
import com.example.studentmanagersystem.base.BaseActivity;
import com.example.studentmanagersystem.entity.Check;
import com.example.studentmanagersystem.entity.User;
import com.example.studentmanagersystem.teacher.presenter.CheckManagerPresenter;
import com.example.studentmanagersystem.teacher.presenter.IView.ICheckManagerView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;

public class CheckManagerActivity extends BaseActivity implements ICheckManagerView {

    private TextView mCheckKeyText;
    private Button mStartCheckBtn;
    private TextView mCountdownText;
    private RecyclerView mCheckedStudentRecycler;
    private RecyclerView mUnCheckStudentRecycler;
    private CheckStudentListAdapter mCheckedListAdapter;
    private CheckStudentListAdapter mUnCheckListAdapter;
    private CheckManagerPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_manager);

        mPresenter = new CheckManagerPresenter(this, this);

        initView();
    }

    private void initView() {
        mCheckKeyText = findViewById(R.id.check_key_text);
        mStartCheckBtn = findViewById(R.id.start_check_btn);
        mCountdownText = findViewById(R.id.countdown_text);
        mCheckedStudentRecycler = findViewById(R.id.checked_list);
        mUnCheckStudentRecycler = findViewById(R.id.un_checked_list);

        mStartCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.startChecked();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mCheckedStudentRecycler.setLayoutManager(layoutManager);

        //对已签到列表配置适配器
        mCheckedListAdapter = new CheckStudentListAdapter();
        mCheckedStudentRecycler.setAdapter(mCheckedListAdapter);

        //对未签到列表配置适配器
        mUnCheckListAdapter = new CheckStudentListAdapter();
        mUnCheckStudentRecycler.setAdapter(mUnCheckListAdapter);
    }

    private void changeView() {
        mCheckKeyText.setVisibility(View.GONE);
        mStartCheckBtn.setVisibility(View.GONE);
        mCountdownText.setVisibility(View.VISIBLE);
        mCheckedStudentRecycler.setVisibility(View.VISIBLE);
        mUnCheckStudentRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "签到创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(BmobException e) {
        Toast.makeText(this, "error: " + e, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(this, "error: " + e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadCheckKeyInfo(String checkKey) {
        mCheckKeyText.setText("签到口令：" + checkKey);
    }

    @Override
    public void startOnSuccess() {
        Toast.makeText(this, "开始签到，五分钟后结束", Toast.LENGTH_SHORT).show();
        changeView();
    }

    @Override
    public void loadCheckedInfo(List<Check> checkList) {
        mCheckedListAdapter.fillList(checkList);
        mCheckedListAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadUnCheckedInfo(List<User> unCheckList) {
        List<Check> unCheckInfoList = new ArrayList<>();
        for (User user : unCheckList) {
            Check check = new Check();
            check.setStudentId(user.getUserId());
            check.setUserName(user.getUserName());
            unCheckInfoList.add(check);
        }

        mUnCheckListAdapter.fillList(unCheckInfoList);
        mUnCheckListAdapter.notifyDataSetChanged();
    }
}
