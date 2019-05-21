package com.example.studentmanagersystem.teacher.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentmanagersystem.R;
import com.example.studentmanagersystem.entity.ChoseCourse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EditGradeListAdapter extends RecyclerView.Adapter<EditGradeListAdapter.EditGradeListViewHolder> {

    private static final String TAG = "EditGradeListAdapter";
    private List<ChoseCourse> mList = new ArrayList<>();
    private EditGradeListListener mListener;
    private EditGradeListViewHolder mHolder;

    @NonNull
    @Override
    public EditGradeListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_edit_student_grade,
                viewGroup, false);
        return new EditGradeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EditGradeListViewHolder holder, final int position) {
        if (position == 0) {
            holder.userIdText.setText("学号");
            holder.studentNameText.setText("学生姓名");
            holder.gradeEdit.setText("成绩");
            holder.gradeEdit.setFocusable(false);
        } else {
            mHolder = holder;
            holder.userIdText.setText(String.valueOf(mList.get(position - 1).getUserId()));
            holder.studentNameText.setText(mList.get(position - 1).getStudentName());
            holder.gradeEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (!hasFocus) {
                        String grade = holder.gradeEdit.getText().toString();
                        if (Pattern.compile("^[0-9]\\d*$").matcher(grade).matches()) {
                            mList.get(position - 1).setGrade(Integer.valueOf(grade));
                        }
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    public void fillLst(List<ChoseCourse> list) {
        mList.clear();
        if (!list.isEmpty()) {
            mList = list;
        }
    }

    public void loadCourseGradeInfo() {
        if (mHolder != null) {
            String grade = mHolder.gradeEdit.getText().toString();
            ChoseCourse course = mList.get(mHolder.getAdapterPosition() - 1);
            if (Pattern.compile("^[0-9]\\d*$").matcher(grade).matches()) {
                course.setGrade(Integer.valueOf(grade));
                mList.set(mHolder.getAdapterPosition() - 1, course);
            }
        }
        mListener.getEditResultListener(mList);
    }

    public void setEditGradeListener(EditGradeListListener listener) {
        mListener = listener;
    }

    public interface EditGradeListListener {
        void getEditResultListener(List<ChoseCourse> result);
    }

    public static class EditGradeListViewHolder extends RecyclerView.ViewHolder {

        private TextView userIdText;
        private TextView studentNameText;
        private EditText gradeEdit;

        public EditGradeListViewHolder(@NonNull View itemView) {
            super(itemView);

            userIdText = itemView.findViewById(R.id.user_id_text);
            studentNameText = itemView.findViewById(R.id.student_name_text);
            gradeEdit = itemView.findViewById(R.id.grade_edit);
        }
    }
}
