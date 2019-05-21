package com.example.studentmanagersystem.student.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseListViewHolder> {

    private List<Course> mCourseList = new ArrayList<>();

    @NonNull
    @Override
    public CourseListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_student_course_list,
                viewGroup, false);
        return new CourseListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseListViewHolder viewHolder, int position) {
        if (position == 0) {
            viewHolder.courseNameText.setText("课程名称");
            viewHolder.courseTeacherText.setText("上课老师");
            viewHolder.courseTimeText.setText("上课时间");
        } else {
            viewHolder.courseNameText.setText(mCourseList.get(position - 1).getCourseName());
            viewHolder.courseTeacherText.setText(mCourseList.get(position - 1).getUserName());
            viewHolder.courseTimeText.setText(mCourseList.get(position - 1).getTime());
        }
    }

    @Override
    public int getItemCount() {
        return mCourseList.size() + 1;
    }

    public void fillList(List<Course> courseList) {
        if (!courseList.isEmpty()) {
            mCourseList = courseList;
        }
    }

    public static class CourseListViewHolder extends RecyclerView.ViewHolder {

        private TextView courseNameText;
        private TextView courseTeacherText;
        private TextView courseTimeText;

        CourseListViewHolder(@NonNull View itemView) {
            super(itemView);

            courseNameText = itemView.findViewById(R.id.course_name);
            courseTeacherText = itemView.findViewById(R.id.course_teacher_name);
            courseTimeText = itemView.findViewById(R.id.course_time);
        }
    }
}
