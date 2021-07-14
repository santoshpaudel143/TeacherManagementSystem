package com.project.teachermanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // database name
    public static final String DATABASE_NAME = "teacher.db";
    // table name
    public static final String TEACHER_TABLE = "teacher_detail";
    public static final String DEPARTMENT_TABLE = "department_detail";
    public static final String REGISTER_TABLE = "register_detail";
    // column name
    public static final String ID = "id";
    public static final String FULL_NAME = "full_name";
    public static final String ADDRESS = "address";
    public static final String CONTACT = "contact";
    public static final String EMAIL = "email";
    public static final String STATUS = "status";
    public static final String DEPARTMENT = "department";
    public static final String SUBJECT = "subject";
    public static final String SALARY = "salary";
    public static final String AGE = "age";
    public static final String DEPARTMENT_NAME = "department_name";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";

    // this is constructor of DatabaseHelper
    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    // here we create a table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TEACHER_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + FULL_NAME + " TEXT, " + ADDRESS + " TEXT, " + CONTACT + " NUMBER, " + EMAIL + " TEXT, " + STATUS + " BOOLEAN DEFAULT 1," + DEPARTMENT + " TEXT, " + SUBJECT + " TEXT, " + SALARY + " NUMBER, " + AGE + " NUMBER)");
        db.execSQL("CREATE TABLE " + DEPARTMENT_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DEPARTMENT_NAME + " TEXT) ");
        db.execSQL("CREATE TABLE " + REGISTER_TABLE + "(" + USER_NAME + " TEXT PRIMARY KEY, " + PASSWORD + " TEXT) ");
    }

    // here we drop and crate a table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TEACHER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DEPARTMENT_TABLE);
        onCreate(db);
    }

    // insert data into Teacher table
    public boolean insertTeacherData(String fullName, String address, String contact, String email, String department, String subject, String salary, String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FULL_NAME, fullName);
        contentValues.put(ADDRESS, address);
        contentValues.put(CONTACT, contact);
        contentValues.put(EMAIL, email);
        contentValues.put(STATUS, "Active");
        contentValues.put(DEPARTMENT, department);
        contentValues.put(SUBJECT, subject);
        contentValues.put(SALARY, salary);
        contentValues.put(AGE, age);
        long result = db.insert(TEACHER_TABLE, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // insert data into department table
    public boolean insertDepartmentData(String departmentName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DEPARTMENT_NAME, departmentName);
        long result = db.insert(DEPARTMENT_TABLE, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // get all data of Teacher table
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TEACHER_TABLE, null);
        return cursor;
    }

    // get all data of Department Table
    public Cursor getAllDepartmentData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DEPARTMENT_TABLE, null);
        return cursor;
    }

    // update data of Teacher Table
    public boolean updateData(String id, String fullName, String address, String contact, String email, String status, String department, String subject, String salary, String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(FULL_NAME, fullName);
        contentValues.put(ADDRESS, address);
        contentValues.put(CONTACT, contact);
        contentValues.put(EMAIL, email);
        contentValues.put(STATUS, status);
        contentValues.put(DEPARTMENT, department);
        contentValues.put(SUBJECT, subject);
        contentValues.put(SALARY, salary);
        contentValues.put(AGE, age);

        db.update(TEACHER_TABLE, contentValues, " id=? ", new String[]{id});
        return true;
    }

    // delete data of Teacher table
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TEACHER_TABLE, " id=? ", new String[]{id});
    }

    // get Department by Id
    public Cursor getDepartmentNameById(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DEPARTMENT_TABLE + " WHERE id=?", new String[]{id});
        return cursor;
    }

    // get Teacher Detail by Id
    public Cursor getTeacherById(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT *  FROM " + TEACHER_TABLE + " WHERE id=?", new String[]{id});
        return cursor;
    }

    // insert data into register table
    public boolean registerTeacher(String userName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, userName);
        contentValues.put(PASSWORD, password);
        long result = db.insert(REGISTER_TABLE, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // check if user already exist or not
    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + REGISTER_TABLE + " where " + USER_NAME + " =?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // validate weather userName and password correct or not
    public boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + REGISTER_TABLE + " where " + USER_NAME + " = ? and " + PASSWORD + "=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}