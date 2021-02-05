package com.nextstacks.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentViewActivity extends AppCompatActivity implements StudentInfoAdapter.StudentDetailClickListener {

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


    private void loadDataToDatabase() {
        ArrayList<StudentInfo> studentInfos = dbHelper.getStudentDetailsFromDatabase(dbHelper.getReadableDatabase());
        StudentInfoAdapter adapter = new StudentInfoAdapter(this, studentInfos);
        adapter.setListener(this);
        mRcStudentInfo.setAdapter(adapter);
    }

    @Override
    public void onEditClicked(StudentInfo studentInfo) {
//        Toast.makeText(StudentViewActivity.this, "Edit Clicked", Toast.LENGTH_LONG).show();

        Intent editIntent = new Intent(StudentViewActivity.this, StudentEntryActivity.class);
        editIntent.putExtra("STUDENT_DATA", studentInfo);
        editIntent.putExtra("IS_EDIT", true);
        startActivityForResult(editIntent, 134);
    }


    @Override
    public void onDeleteClicked(StudentInfo studentInfo) {
//        Toast.makeText(StudentViewActivity.this, "Delete Clicked", Toast.LENGTH_LONG).show();
        dbHelper.deleteStudentData(dbHelper.getWritableDatabase(), studentInfo);
        loadDataToDatabase();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 134){
            if(resultCode == Activity.RESULT_OK){
                loadDataToDatabase();
            }else{
                Toast.makeText(StudentViewActivity.this, "User Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }
}