package com.project.teachermanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTeacher extends AppCompatActivity {

    DatabaseHelper myDb;
    Validator validator;
    EditText id, fullName, address, contact, email, department, subject, salary, age;
    Button save, updateData, view, delete;
    boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.save);
        setContentView(R.layout.activity_add_teacher);
        getSupportActionBar().setTitle(" Add Teacher Detail");
        myDb = new DatabaseHelper(this);

        id = findViewById(R.id.id);
        fullName = findViewById(R.id.full_name);
        address = findViewById(R.id.address);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        department = findViewById(R.id.department);
        subject = findViewById(R.id.subject);
        salary = findViewById(R.id.salary);
        age = findViewById(R.id.age);
        save = findViewById(R.id.save);
        saveData();
    }

    public void saveData() {
        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String validationMessage;
                        validationMessage = validator.saveValidate(fullName.getText().toString(), address.getText().toString(), contact.getText().toString(), email.getText().toString(), department.getText().toString(), subject.getText().toString(), salary.getText().toString(), age.getText().toString());
                        if (validationMessage == null) {
                            boolean isInserted = myDb.insertTeacherData(fullName.getText().toString(), address.getText().toString(), contact.getText().toString(), email.getText().toString(), department.getText().toString(), subject.getText().toString(), salary.getText().toString(), age.getText().toString());
                            if (isInserted) {
                                Toast.makeText(AddTeacher.this, "Date Saved Successfully", Toast.LENGTH_LONG).show();
                                back();
                            } else {
                                Toast.makeText(AddTeacher.this, "Date could not Saved", Toast.LENGTH_SHORT).show();
                            }
                            // save into department table
                            Cursor cursor = myDb.getAllDepartmentData();
                            if (cursor.getCount() == 0) {
                                myDb.insertDepartmentData(department.getText().toString());
                            } else {
                                while (cursor.moveToNext()) {
                                    if (cursor.getString(1).equals(department.getText().toString())) {
                                        status = true;
                                        break;
                                    }
                                }
                                if (!status) {
                                    myDb.insertDepartmentData(department.getText().toString());
                                }
                            }
                        } else {
                            Toast.makeText(AddTeacher.this, validationMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    private void back() {
        Intent intent = new Intent(this, TeacherDetail.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(this, TeacherDetail.class);
        startActivity(intent);
    }

    public void save(View view) {
    }
}