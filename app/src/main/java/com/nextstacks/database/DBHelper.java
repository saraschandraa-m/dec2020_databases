package com.nextstacks.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "student_detail";
    private static final String COL_ID = "id";
    private static final String COL_STUDENT_ID = "student_id";
    private static final String COL_STUDENT_NAME = "student_name";
    private static final String COL_STUDENT_INSTITUTE = "student_institute";
    private static final String COL_STUDENT_AGE = "student_age";
    private static final String COL_STUDENT_PHONE = "student_phone";

    //CREATE TABLE student_detail(id INTEGER PRIMARY KEY AUTOINCREMENT,student_id TEXT,student_name TEXT,student_institute TEXT,student_age TEXT,student_phone INTEGER);

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_STUDENT_AGE +
            " INTEGER," + COL_STUDENT_NAME + " TEXT," + COL_STUDENT_ID + " TEXT," + COL_STUDENT_INSTITUTE + " TEXT," + COL_STUDENT_PHONE +
            " INTEGER)";


    public DBHelper(@Nullable Context context) {
        super(context, "studentinfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertDataToDatabase(StudentInfo studentInfo, SQLiteDatabase database) {
        ContentValues cv = new ContentValues();
        cv.put(COL_STUDENT_ID, studentInfo.studentIDCardNo);
        cv.put(COL_STUDENT_NAME, studentInfo.studentName);
        cv.put(COL_STUDENT_INSTITUTE, studentInfo.studentInstitute);
        cv.put(COL_STUDENT_AGE, studentInfo.studentAge);
        cv.put(COL_STUDENT_PHONE, studentInfo.studentPhoneNumber);
        database.insert(TABLE_NAME, null, cv);
    }


    public ArrayList<StudentInfo> getStudentDetailsFromDatabase(SQLiteDatabase database) {
        ArrayList<StudentInfo> studentsList = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                StudentInfo newStudentInfo = new StudentInfo();
                newStudentInfo.id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                newStudentInfo.studentName = cursor.getString(cursor.getColumnIndex(COL_STUDENT_NAME));
                newStudentInfo.studentInstitute = cursor.getString(cursor.getColumnIndex(COL_STUDENT_INSTITUTE));
                newStudentInfo.studentPhoneNumber = cursor.getLong(cursor.getColumnIndex(COL_STUDENT_PHONE));
                newStudentInfo.studentAge = cursor.getInt(cursor.getColumnIndex(COL_STUDENT_AGE));
                newStudentInfo.studentIDCardNo = cursor.getString(cursor.getColumnIndex(COL_STUDENT_ID));

                studentsList.add(newStudentInfo);
            } while (cursor.moveToNext());
        }

        cursor.close();


//        while(cursor.moveToNext()){
//
//        }


        return studentsList;
    }

    public void deleteStudentData(SQLiteDatabase database, StudentInfo studentInfo) {
        database.delete(TABLE_NAME, COL_ID + " = " + studentInfo.id, null);
    }


    public void updateStudentData(SQLiteDatabase database, StudentInfo studentInfo) {
        ContentValues cv = new ContentValues();
        cv.put(COL_STUDENT_AGE, studentInfo.studentAge);
        cv.put(COL_STUDENT_INSTITUTE, studentInfo.studentInstitute);
        cv.put(COL_STUDENT_ID, studentInfo.studentIDCardNo);
        cv.put(COL_STUDENT_PHONE, studentInfo.studentPhoneNumber);
        cv.put(COL_STUDENT_NAME, studentInfo.studentName);

        database.update(TABLE_NAME, cv, COL_ID + "=" + studentInfo.id, null);
    }

}
