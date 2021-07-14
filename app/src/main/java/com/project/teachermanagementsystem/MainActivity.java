package com.project.teachermanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Integer backButtonCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(" Welcome");

    }

    // to exit when back button is pressed twice
    @Override
    public void onBackPressed() {
        if (backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press twice to exit", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    // go to register page
    public void register(View view) {
        Intent intent = new Intent(this, RegisterTeacher.class);
        startActivity(intent);
    }

    // go to login page
    public void login(View view) {
        Intent intent = new Intent(this, LoginTeacher.class);
        startActivity(intent);
    }
}