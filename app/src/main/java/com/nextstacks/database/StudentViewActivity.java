package com.nextstacks.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class StudentViewActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private RecyclerView mRcStudentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view);

        mRcStudentInfo = findViewById(R.id.rc_student_infos);
        mRcStudentInfo.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

//        mRcStudentInfo.setLayoutManager(new GridLayoutManager(this, 3));
//        mRcStudentInfo.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        dbHelper = new DBHelper(this);

        loadDataToDatabase();
    }


    private void loadDataToDatabase(){

        ArrayList<StudentInfo> studentInfos = dbHelper.getStudentDetailsFromDatabase(dbHelper.getReadableDatabase());
        StudentInfoAdapter adapter = new StudentInfoAdapter(this, studentInfos);

        mRcStudentInfo.setAdapter(adapter);
    }
}