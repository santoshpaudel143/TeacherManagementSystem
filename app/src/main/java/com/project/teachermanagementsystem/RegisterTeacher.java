package com.project.teachermanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterTeacher extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText userName, password, retypePassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.login);
        setContentView(R.layout.activity_register_teacher);
        getSupportActionBar().setTitle(" Registration");

        myDb = new DatabaseHelper(this);

        userName = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        retypePassword = findViewById(R.id.retype_password);
        register = findViewById(R.id.register);
        registerAdmin();
    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void register(View view) {
    }

    // register user
    public void registerAdmin() {
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (userName.getText().toString().equals("") || password.getText().toString().equals("") || retypePassword.getText().toString().equals("")) {
                            Toast.makeText(RegisterTeacher.this, "Enter All Filed", Toast.LENGTH_LONG).show();
                            password.setText("");
                            retypePassword.setText("");
                        } else {
                            if (password.getText().toString().equals(retypePassword.getText().toString())) {
                                boolean isUserExist = myDb.checkUsername(userName.getText().toString());
                                if (!isUserExist) {
                                    Boolean isRegistered = myDb.registerTeacher(userName.getText().toString(), password.getText().toString());
                                    if (isRegistered) {
                                        Toast.makeText(RegisterTeacher.this, "User Register Successfully", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginTeacher.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(RegisterTeacher.this, "User could not Registered", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(RegisterTeacher.this, "Already Exist User, Login!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginTeacher.class);
                                    startActivity(intent);
                                }
                            } else {
                                Toast.makeText(RegisterTeacher.this, "Password does not match", Toast.LENGTH_LONG).show();
                                password.setText("");
                                retypePassword.setText("");
                            }
                        }
                    }
                }
        );
    }
}