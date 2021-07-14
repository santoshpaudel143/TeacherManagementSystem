package com.project.teachermanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewTeacher extends AppCompatActivity {

    DatabaseHelper myDb;
    Button viewAll, viewById, viewAllDepartment, viewDepartmentById;
    EditText viewId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.view);
        setContentView(R.layout.activity_view_teacher);
        getSupportActionBar().setTitle(" View Teacher Detail");

        myDb = new DatabaseHelper(this);
        viewAll = findViewById(R.id.viewAll);
        viewById = findViewById(R.id.viewById);
        viewId = findViewById(R.id.viewId);
        viewAllDepartment = findViewById(R.id.view_all_department);
        viewDepartmentById = findViewById(R.id.view_department_by_id);
        viewAllData();
        viewByIdData();
        allDepartment();
        getDepartmentById();
    }

    public void viewAllData() {
        viewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor cursor = myDb.getAllData();
                        if (cursor.getCount() == 0) {
                            showMessage("Error", " Not Data Found");
                            Toast.makeText(ViewTeacher.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (cursor.moveToNext()) {
                            buffer.append("Id: " + cursor.getString(0) + "\n");
                            buffer.append("Full Name: " + cursor.getString(1) + "\n");
                            buffer.append("Address: " + cursor.getString(2) + "\n");
                            buffer.append("Contact: " + cursor.getString(3) + "\n");
                            buffer.append("Email: " + cursor.getString(4) + "\n");
                            buffer.append("Status: " + cursor.getString(5) + "\n");
                            buffer.append("Department: " + cursor.getString(6) + "\n");
                            buffer.append("Subject: " + cursor.getString(7) + "\n");
                            buffer.append("Salary: " + cursor.getString(8) + "\n");
                            buffer.append("Age: " + cursor.getString(9) + "\n\n");
                        }
                        showMessage("Teacher Detail ", buffer.toString());
                    }
                }
        );
    }

    public void viewByIdData() {
        viewById.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor cursor = myDb.getTeacherById(viewId.getText().toString());
                        if (cursor.getCount() == 0) {
                            showMessage("Error", " Not Data Found");
                            Toast.makeText(ViewTeacher.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (cursor.moveToNext()) {
                            buffer.append("Id: " + cursor.getString(0) + "\n");
                            buffer.append("Full Name: " + cursor.getString(1) + "\n");
                            buffer.append("Address: " + cursor.getString(2) + "\n");
                            buffer.append("Contact: " + cursor.getString(3) + "\n");
                            buffer.append("Email: " + cursor.getString(4) + "\n");
                            buffer.append("Status: " + cursor.getString(5) + "\n");
                            buffer.append("Department: " + cursor.getString(6) + "\n");
                            buffer.append("Subject: " + cursor.getString(7) + "\n");
                            buffer.append("Salary: " + cursor.getString(8) + "\n");
                            buffer.append("Age: " + cursor.getString(9) + "\n\n");
                        }
                        showMessage("Teacher Detail", buffer.toString());
                    }
                }
        );
    }

    public void allDepartment() {
        viewAllDepartment.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor cursor = myDb.getAllDepartmentData();
                        if (cursor.getCount() == 0) {
                            showMessage("Error", " Not Data Found");
                            Toast.makeText(ViewTeacher.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (cursor.moveToNext()) {
                            buffer.append("Id: " + cursor.getString(0) + "\n");
                            buffer.append("Department Name: " + cursor.getString(1) + "\n\n");
                        }
                        showMessage("Department Detail ", buffer.toString());
                    }
                }
        );
    }

    public void getDepartmentById() {
        viewDepartmentById.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor cursor = myDb.getDepartmentNameById(viewId.getText().toString());
                        if (cursor.getCount() == 0) {
                            showMessage("Error", " Not Data Found");
                            Toast.makeText(ViewTeacher.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (cursor.moveToNext()) {
                            buffer.append("Id: " + cursor.getString(0) + "\n");
                            buffer.append("Department Name: " + cursor.getString(1) + "\n\n");
                        }
                        showMessage("Department Detail ", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void viewAll(View view) {
    }

    public void viewById(View view) {
    }

    public void back(View view) {
        Intent intent = new Intent(this, TeacherDetail.class);
        startActivity(intent);
    }
}