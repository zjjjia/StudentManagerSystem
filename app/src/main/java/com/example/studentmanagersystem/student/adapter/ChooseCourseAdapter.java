package com.example.studentmanagersystem.student.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class ChooseCourseAdapter extends RecyclerView.Adapter<ChooseCourseAdapter.ChooseCourseViewHolder> {

    private List<Course> mCourseList = new ArrayList<>();
    private ChooseCourseListener mListener;
    private List<String> mchoseCourseList = new ArrayList<>();

    @NonNull
    @Override
    public ChooseCourseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_choose_crouse_list,
                viewGroup, false);

        return new ChooseCourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseCourseViewHolder viewHolder, final int position) {
        if (position == 0) {
            viewHolder.courseNameText.setText("课程名称");
            viewHolder.courseTeacherText.setText("教师姓名");
            viewHolder.courseTimeText.setText("上课时间");
            viewHolder.chooseCheckBox.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.courseNameText.setText(mCourseList.get(position - 1).getCourseName());
            viewHolder.courseTeacherText.setText(mCourseList.get(position - 1).getUserName());
            viewHolder.courseTimeText.setText(mCourseList.get(position - 1).getTime());
        }
        viewHolder.chooseCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mchoseCourseList.add(mCourseList.get(position - 1).getObjectId());
                } else {
                    mchoseCourseList.remove(mchoseCourseList.get(position - 1));
                }
            }
        });

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

    public void loadChooseInfo(){
        if(mListener != null){
            mListener.onChooseListener(mchoseCourseList);
        }
    }

    public void setChooseCourseListener(ChooseCourseListener listener) {
        mListener = listener;
    }

    public interface ChooseCourseListener {
        void onChooseListener(List<String> choseList);
    }

    static class ChooseCourseViewHolder extends RecyclerView.ViewHolder {

        private TextView courseNameText;
        private TextView courseTeacherText;
        private TextView courseTimeText;
        private CheckBox chooseCheckBox;

        ChooseCourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseNameText = itemView.findViewById(R.id.course_name);
            courseTeacherText = itemView.findViewById(R.id.course_teacher_name);
            courseTimeText = itemView.findViewById(R.id.course_time);
            chooseCheckBox = itemView.findViewById(R.id.course_check_box);
        }
    }
}
