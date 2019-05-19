package com.example.studentmanagersystem.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.bmboTable.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseListHolder> {

    private List<Course> mCourseList = new ArrayList<>();

    @NonNull
    @Override
    public CourseListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_course_list, viewGroup, false);

        return new CourseListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseListHolder holder, int position) {
        holder.courseNameText.setText(mCourseList.get(position).getCourseName());
    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }

    public void fillList(List<Course> courseList) {
        if (!mCourseList.isEmpty()) {
            mCourseList = courseList;
        }
    }

    static class CourseListHolder extends RecyclerView.ViewHolder {

        public TextView courseNameText;
        public TextView courseTimeText;

        public CourseListHolder(@NonNull View itemView) {
            super(itemView);

            courseNameText = itemView.findViewById(R.id.course_name);
            courseTimeText = itemView.findViewById(R.id.course_time);
        }
    }
}
