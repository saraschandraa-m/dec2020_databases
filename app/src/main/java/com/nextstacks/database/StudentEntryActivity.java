package com.nextstacks.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentEntryActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    private boolean isEdit = false;
    private StudentInfo editStudentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_entry);

        final EditText mEtStudentIDcardNo = findViewById(R.id.et_student_id_card_no);
        final EditText mEtStudentName = findViewById(R.id.et_student_name);
        final EditText mEtStudentInstitute = findViewById(R.id.et_student_institute);
        final EditText mEtStudentAge = findViewById(R.id.et_student_age);
        final EditText mEtStudentPhone = findViewById(R.id.et_student_phone);

        Button mBtnEnterData = findViewById(R.id.btn_enter_data);
        Button mBtnGetData = findViewById(R.id.btn_get_data);

        dbHelper = new DBHelper(StudentEntryActivity.this);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            isEdit = data.getBoolean("IS_EDIT");
            editStudentInfo = (StudentInfo) data.getSerializable("STUDENT_DATA");

            if (isEdit) {
                mEtStudentIDcardNo.setText(editStudentInfo.studentIDCardNo);
                mEtStudentInstitute.setText(editStudentInfo.studentInstitute);
                mEtStudentAge.setText(String.valueOf(editStudentInfo.studentAge));
                mEtStudentName.setText(editStudentInfo.studentName);
                mEtStudentPhone.setText(String.valueOf(editStudentInfo.studentPhoneNumber));
            }
        }


        mBtnEnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idCardNo = mEtStudentIDcardNo.getText().toString();
                String studentName = mEtStudentName.getText().toString();
                String studentInstitute = mEtStudentInstitute.getText().toString();
                String studentAge = mEtStudentAge.getText().toString();
                String studentPhone = mEtStudentPhone.getText().toString();

                StudentInfo student = new StudentInfo();
                student.studentIDCardNo = idCardNo;
                student.studentAge = Integer.parseInt(studentAge);
                student.studentPhoneNumber = Long.parseLong(studentPhone);
                student.studentInstitute = studentInstitute;
                student.studentName = studentName;


                if (!isEdit) {
                    dbHelper.insertDataToDatabase(student, dbHelper.getWritableDatabase());
                } else {
                    student.id = editStudentInfo.id;

                    dbHelper.updateStudentData(dbHelper.getWritableDatabase(), student);
                    setResult(Activity.RESULT_OK);
                    finish();
                }


                mEtStudentAge.setText("");
                mEtStudentIDcardNo.setText("");
                mEtStudentInstitute.setText("");
                mEtStudentName.setText("");
                mEtStudentPhone.setText("");
//                student.setStudentIDCardNo(idCardNo);
//                student.setStudentAge(Integer.parseInt(studentAge));
            }
        });


        mBtnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ArrayList<StudentInfo> studentList = dbHelper.getStudentDetailsFromDatabase(dbHelper.getReadableDatabase());

//                Toast.makeText(StudentEntryActivity.this, ""+studentList.size(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(StudentEntryActivity.this, StudentViewActivity.class));
            }
        });

    }
}