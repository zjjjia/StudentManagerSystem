package com.example.studentmanagersystem.teacher.adapter;

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

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseListHolder> {

    private static final String TAG = "CourseListAdapter";
    private List<Course> mCourseList = new ArrayList<>();

    @NonNull
    @Override
    public CourseListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_course_list,
                viewGroup, false);

        return new CourseListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseListHolder holder, int position) {
        if (position == 0) {
            holder.courseNameText.setText("课程名称");
            holder.courseTimeText.setText("上课时间");
        } else {
            holder.courseNameText.setText(mCourseList.get(position - 1).getCourseName());
            holder.courseTimeText.setText(mCourseList.get(position - 1).getTime());
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

    static class CourseListHolder extends RecyclerView.ViewHolder {

        TextView courseNameText;
        TextView courseTimeText;

        CourseListHolder(@NonNull View itemView) {
            super(itemView);

            courseNameText = itemView.findViewById(R.id.course_name);
            courseTimeText = itemView.findViewById(R.id.course_time);
        }
    }
}
