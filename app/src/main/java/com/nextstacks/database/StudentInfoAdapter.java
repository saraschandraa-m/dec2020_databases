package com.nextstacks.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentInfoAdapter extends RecyclerView.Adapter<StudentInfoAdapter.StudentInfoHolder> {


    private Context context;
    private ArrayList<StudentInfo> studentInfoList;

    public StudentInfoAdapter(Context context, ArrayList<StudentInfo> studentInfoList){
        this.context = context;
        this.studentInfoList = studentInfoList;
    }

    public StudentInfoAdapter(){

    }


    public void setContext(Context context) {
        this.context = context;
    }

    public void setStudentInfoList(ArrayList<StudentInfo> studentInfoList) {
        this.studentInfoList = studentInfoList;
    }

    @NonNull
    @Override
    public StudentInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cell_student_info, parent, false);
        StudentInfoHolder holder = new StudentInfoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentInfoHolder holder, int position) {
        StudentInfo student = studentInfoList.get(position);

        holder.mTvStudentID.setText(student.studentIDCardNo);
        holder.mTvStudentName.setText(student.studentName);
        holder.mTvStudentPhoneNumber.setText(String.valueOf(student.studentPhoneNumber));
    }

    @Override
    public int getItemCount() {
        return studentInfoList.size();
    }

    class StudentInfoHolder extends RecyclerView.ViewHolder{

        private TextView mTvStudentID;
        private TextView mTvStudentName;
        private TextView mTvStudentPhoneNumber;

        public StudentInfoHolder(@NonNull View itemView) {
            super(itemView);

            mTvStudentID = itemView.findViewById(R.id.tv_student_id_card_no);
            mTvStudentName = itemView.findViewById(R.id.tv_student_name);
            mTvStudentPhoneNumber = itemView.findViewById(R.id.tv_student_phone_number);
        }
    }
}
