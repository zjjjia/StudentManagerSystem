package com.example.studentmanagersystem.teacher.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.entity.Check;

import java.util.ArrayList;
import java.util.List;

public class CheckStudentListAdapter extends RecyclerView.Adapter<CheckStudentListAdapter.CheckStudentViewHolder> {

    private List<Check> mStudentList = new ArrayList<>();

    @NonNull
    @Override
    public CheckStudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_check_student_list,
                viewGroup, false);

        return new CheckStudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckStudentViewHolder viewHolder, int position) {
        if (position == 0) {
            viewHolder.studentIdText.setText("学号");
            viewHolder.studentNameText.setText("姓名");
            viewHolder.checkTimeText.setText("签到时间");
        } else {
            viewHolder.studentIdText.setText(mStudentList.get(position - 1).getStudentId());
            viewHolder.studentNameText.setText(mStudentList.get(position - 1).getUserName());
            viewHolder.checkTimeText.setText(mStudentList.get(position - 1).getCreatedAt());
        }
    }

    @Override
    public int getItemCount() {
        return mStudentList.size() + 1;
    }

    public void fillList(List<Check> studentList) {
        if (!studentList.isEmpty()) {
            mStudentList = studentList;
        }
    }



    public static class CheckStudentViewHolder extends RecyclerView.ViewHolder {

        private TextView studentIdText;
        private TextView studentNameText;
        private TextView checkTimeText;

        public CheckStudentViewHolder(@NonNull View itemView) {
            super(itemView);

            studentIdText = itemView.findViewById(R.id.student_id_text);
            studentNameText = itemView.findViewById(R.id.student_name_text);
            checkTimeText = itemView.findViewById(R.id.check_time_text);
        }
    }
}
