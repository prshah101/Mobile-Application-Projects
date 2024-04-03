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
import android.widget.Toast;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetails); // Replace "your_layout" with the actual name of your XML layout file

        // Accessing the EditText fields and buttons
        //EditText taskTitleEditText = findViewById(R.id.taskTitle);
        EditText dueDateEditText = findViewById(R.id.dueDate);
        //EditText descriptionEditText = findViewById(R.id.description);
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

            try {
                EditText taskTitleEditText = findViewById(R.id.taskTitle);
                EditText descriptionEditText = findViewById(R.id.description);
                // Attempt to create a ToDoItem
                ToDoItem todoItem = new ToDoItem(taskTitleEditText.getText().toString(), descriptionEditText.getText().toString());
                // If successful, display the ToDoItem using a toast message
                //Toast.makeText(TaskActivity.this, todoItem.toString(), Toast.LENGTH_SHORT).show();

                DatabaseHelper dataBaseHelper = new DatabaseHelper(TaskActivity.this);

                boolean success = dataBaseHelper.addOne(todoItem);
//                Toast.makeText(TaskActivity.this, "Sucess" + success, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            } catch (Exception e) {
                // If an exception occurs, handle it here
                Toast.makeText(TaskActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}
