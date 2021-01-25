package com.nextstacks.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "student_detail";
    private static final String COL_ID = "id";
    private static final String COL_STUDENT_ID = "student_id";
    private static final String COL_STUDENT_NAME = "student_name";
    private static final String COL_STUDENT_INSTITUTE = "student_institute";
    private static final String COL_STUDENT_AGE = "student_age";
    private static final String COL_STUDENT_PHONE = "student_phone";

    //CREATE TABLE student_detail(id INTEGER PRIMARY KEY AUTOINCREMENT,student_id TEXT,student_name TEXT,student_institute TEXT,student_age TEXT,student_phone INTEGER);

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_STUDENT_AGE+
            " INTEGER,"+COL_STUDENT_NAME + " TEXT," + COL_STUDENT_ID + " TEXT," +COL_STUDENT_INSTITUTE + " TEXT," + COL_STUDENT_PHONE +
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

    public void insertDataToDatabase(StudentInfo studentInfo, SQLiteDatabase database){
        ContentValues cv = new ContentValues();
        cv.put(COL_STUDENT_ID, studentInfo.studentIDCardNo);
        cv.put(COL_STUDENT_NAME, studentInfo.studentName);
        cv.put(COL_STUDENT_INSTITUTE, studentInfo.studentInstitute);
        cv.put(COL_STUDENT_AGE, studentInfo.studentAge);
        cv.put(COL_STUDENT_PHONE, studentInfo.studentPhoneNumber);
        database.insert(TABLE_NAME, null, cv);
    }
}
