package com.nextstacks.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentEntryActivity extends AppCompatActivity {

    private DBHelper dbHelper;

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

        dbHelper = new DBHelper(StudentEntryActivity.this);

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


                dbHelper.insertDataToDatabase(student, dbHelper.getWritableDatabase());


//                student.setStudentIDCardNo(idCardNo);
//                student.setStudentAge(Integer.parseInt(studentAge));
            }
        });
    }
}