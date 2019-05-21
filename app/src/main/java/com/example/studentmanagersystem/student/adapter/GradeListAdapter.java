package com.example.studentmanagersystem.student.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.entity.Grade;

import java.util.ArrayList;
import java.util.List;

public class GradeListAdapter extends RecyclerView.Adapter<GradeListAdapter.GradeListViewHolder> {

    private List<Grade> mGradeList = new ArrayList<>();

    @NonNull
    @Override
    public GradeListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grade_list,
                viewGroup, false);
        return new GradeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeListViewHolder holder, int position) {
        if (position == 0) {
            holder.courseNameText.setText("课程名称");
            holder.courseGradeText.setText("成绩");
        } else {
            holder.courseNameText.setText(mGradeList.get(position - 1).getCourseName());
            holder.courseGradeText.setText(String.valueOf(mGradeList.get(position - 1).getGrade()));
        }
    }

    @Override
    public int getItemCount() {
        return mGradeList.size() + 1;
    }

    public void fillList(List<Grade> gradeList) {
        if (!gradeList.isEmpty()) {
            mGradeList = gradeList;
        }
    }

    public static class GradeListViewHolder extends RecyclerView.ViewHolder {

        private TextView courseNameText;
        private TextView courseGradeText;

        public GradeListViewHolder(@NonNull View itemView) {
            super(itemView);

            courseNameText = itemView.findViewById(R.id.course_name);
            courseGradeText = itemView.findViewById(R.id.course_grade);
        }
    }
}
