package com.project.teachermanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class TeacherDetail extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);
        setContentView(R.layout.activity_teacher_detail);
        getSupportActionBar().setTitle(" Teacher Management System");
        myDb = new DatabaseHelper(this);
    }

    public void add(View view) {
        Intent intent = new Intent(this, AddTeacher.class);
        startActivity(intent);
    }

    public void edit(View view) {
        Intent intent = new Intent(this, EditTeacher.class);
        startActivity(intent);
    }

    public void delete(View view) {
        Intent intent = new Intent(this, DeleteTeacher.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void view(View view) {
        Intent intent = new Intent(this, ViewTeacher.class);
        startActivity(intent);
    }
}