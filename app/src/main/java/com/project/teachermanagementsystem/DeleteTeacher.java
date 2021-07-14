package com.project.teachermanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteTeacher extends AppCompatActivity {

    DatabaseHelper myDb;
    Button remove;
    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.delete);
        setContentView(R.layout.activity_delete_teacher);
        getSupportActionBar().setTitle(" Delete Teacher Detail");
        myDb = new DatabaseHelper(this);

        id = findViewById(R.id.deleteId);
        remove = findViewById(R.id.remove);
        delete();
    }

    public void delete() {
        remove.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRow = myDb.deleteData(id.getText().toString());
                        if (deletedRow > 0) {
                            Toast.makeText(DeleteTeacher.this, "Date Deleted Successfully", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(DeleteTeacher.this, "Date could not Delete", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void back(View view) {
        Intent intent = new Intent(this, TeacherDetail.class);
        startActivity(intent);
    }

    public void remove(View view) {
    }
}