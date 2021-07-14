package com.project.teachermanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditTeacher extends AppCompatActivity {

    Button edit;
    DatabaseHelper myDb;
    Validator validator;
    EditText id, fullName, address, contact, email, department, subject, salary, age;
    Spinner status;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.update);
        setContentView(R.layout.activity_edit_teacher);
        getSupportActionBar().setTitle(" Edit Teacher Detail");
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
        edit = findViewById(R.id.edit);

        // creating dropdown for status
        status = findViewById(R.id.status);
        String[] items = new String[]{"Active", "Inactive"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(EditTeacher.this, android.R.layout.simple_spinner_dropdown_item, items);
        status.setAdapter(arrayAdapter);

        editData();

        // creating dropdown for status
//        Spinner spinner = (Spinner) findViewById(R.id.status);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(EditTeacher.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.listStatusTest));
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(arrayAdapter);
    }

    public void editData() {
        edit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String validationMessage;
                        validationMessage = validator.editValidate(id.getText().toString(), fullName.getText().toString(), address.getText().toString(), contact.getText().toString(), email.getText().toString(), department.getText().toString(), subject.getText().toString(), salary.getText().toString(), age.getText().toString());
                        if (validationMessage == null) {
                            boolean isUpdated = myDb.updateData(id.getText().toString(), fullName.getText().toString(), address.getText().toString(), contact.getText().toString(), email.getText().toString(), status.getSelectedItem().toString(), department.getText().toString(), subject.getText().toString(), salary.getText().toString(), age.getText().toString());
                            if (isUpdated) {
                                Toast.makeText(EditTeacher.this, "Date Updated Successfully", Toast.LENGTH_LONG).show();
                                back();
                            } else {
                                Toast.makeText(EditTeacher.this, "Date could not Update", Toast.LENGTH_SHORT).show();
                            }
                            // save into department table
                            Cursor cursor = myDb.getAllDepartmentData();
                            if (cursor.getCount() == 0) {
                                myDb.insertDepartmentData(department.getText().toString());
                            } else {
                                while (cursor.moveToNext()) {
                                    if (cursor.getString(1).equals(department.getText().toString())) {
                                        flag = true;
                                        break;
                                    }
                                }
                                if (!flag) {
                                    myDb.insertDepartmentData(department.getText().toString());
                                }
                            }
                        } else {
                            Toast.makeText(EditTeacher.this, validationMessage, Toast.LENGTH_SHORT).show();
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

    public void edit(View view) {

    }
}