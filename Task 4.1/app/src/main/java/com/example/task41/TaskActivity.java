package com.example.task41;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetails); // Replace "your_layout" with the actual name of your XML layout file

        // Accessing the EditText fields and buttons
        EditText taskTitleEditText = findViewById(R.id.taskTitle);
        EditText dueDateEditText = findViewById(R.id.dueDate);
        EditText descriptionEditText = findViewById(R.id.description);
        Button editSaveBtn = findViewById(R.id.editSaveBtn);
        Button deleteTaskBtn = findViewById(R.id.deleteTaskBtn);

        // Set OnClickListener for the button
        editSaveBtn.setOnClickListener(this);
        deleteTaskBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.editSaveBtn){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
