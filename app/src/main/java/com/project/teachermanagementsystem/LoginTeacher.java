package com.project.teachermanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginTeacher extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText userName, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.login);
        setContentView(R.layout.activity_login_teacher);
        getSupportActionBar().setTitle(" Login");
        myDb = new DatabaseHelper(this);

        userName = findViewById(R.id.login_user_name);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login);
        userLogin();
    }

    public void login(View view) {
    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void userLogin() {
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user = userName.getText().toString();
                        String pass = password.getText().toString();
                        if (user.equals("") || pass.equals("")) {
                            Toast.makeText(LoginTeacher.this, "Enter All Filed", Toast.LENGTH_LONG).show();
                            password.setText("");
                        } else {
                            boolean isRegister = myDb.checkUsernamePassword(userName.getText().toString(), password.getText().toString());
                            if (isRegister) {
                                Toast.makeText(LoginTeacher.this, "Login Successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), TeacherDetail.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginTeacher.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
                                password.setText("");
                            }
                        }
                    }
                }
        );
    }
}