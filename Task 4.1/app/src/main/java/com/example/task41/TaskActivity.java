package com.example.task41;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.widget.Toast;

import java.text.BreakIterator;
import java.text.DateFormat;
import java.util.Calendar;

public class TaskActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    EditText taskTitleEditText;
    EditText descriptionEditText;

    TextView dueDateText;

    Button dueDateButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetails); // Replace "your_layout" with the actual name of your XML layout file

        // Accessing the EditText fields and buttons
        dueDateButton = findViewById(R.id.dueDateButton);
        dueDateText = findViewById(R.id.dueDateText);

        taskTitleEditText = findViewById(R.id.taskTitle);
        descriptionEditText = findViewById(R.id.description);
        Button editSaveBtn = findViewById(R.id.editSaveBtn);
        Button deleteTaskBtn = findViewById(R.id.deleteTaskBtn);
        Button backtoMainBtn = findViewById(R.id.backBtn);

        // Retrieve the value passed from MainActivity
        String selectedItemTitle = getIntent().getStringExtra("selectedItemTitle");
        String selectedItemDescription = getIntent().getStringExtra("selectedItemDescription");


        taskTitleEditText.setText(selectedItemTitle);
        descriptionEditText.setText(selectedItemDescription);

        // Set OnClickListener for the button
        editSaveBtn.setOnClickListener(this);
        deleteTaskBtn.setOnClickListener(this);
        backtoMainBtn.setOnClickListener(this);
        dueDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Please note that use your package name here
                tutorials.droid.datepicker.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new tutorials.droid.datepicker.DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
        dueDateText.setText(selectedDate);
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        boolean itemSelected = getIntent().getBooleanExtra("itemSelected", false);

        if (clickedButton.getId() == R.id.editSaveBtn && !itemSelected) {

            try {
                // Attempt to create a ToDoItem
                ToDoItem todoItem = new ToDoItem(taskTitleEditText.getText().toString(), descriptionEditText.getText().toString(), dueDateText.getText().toString());
                DatabaseHelper dataBaseHelper = new DatabaseHelper(TaskActivity.this);

                boolean success = dataBaseHelper.addOne(todoItem);

                if (success) {
                    Toast.makeText(TaskActivity.this, "Task added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TaskActivity.this, "Failed to add task", Toast.LENGTH_SHORT).show();
                }

                changeViewToMainActivity();

            } catch (Exception e) {
                // If an exception occurs, handle it here
                Toast.makeText(TaskActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (clickedButton.getId() == R.id.editSaveBtn && itemSelected) {
            try {
                // Attempt to create a ToDoItem
                ToDoItem todoItem = new ToDoItem(taskTitleEditText.getText().toString(), descriptionEditText.getText().toString(), dueDateText.getText().toString());
                DatabaseHelper dataBaseHelper = new DatabaseHelper(TaskActivity.this);

                boolean success = dataBaseHelper.updateOne(todoItem);
                if (success) {
                    Toast.makeText(TaskActivity.this, "Task updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TaskActivity.this, "Failed to update task", Toast.LENGTH_SHORT).show();
                }
                changeViewToMainActivity();

            } catch (Exception e) {
                // If an exception occurs, handle it here
                Toast.makeText(TaskActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (clickedButton.getId() == R.id.backBtn) {
            changeViewToMainActivity();
        } else if (clickedButton.getId() == R.id.deleteTaskBtn) {
            try {
                // Create an instance of DatabaseHelper
                DatabaseHelper databaseHelper = new DatabaseHelper(TaskActivity.this);

                // Create a ToDoItem object
                ToDoItem todoItem = new ToDoItem(taskTitleEditText.getText().toString(), descriptionEditText.getText().toString(), dueDateText.getText().toString());

                // Attempt to delete the ToDoItem
                boolean success = databaseHelper.deleteOne(todoItem);

                if (success) {
                    Toast.makeText(TaskActivity.this, "Task deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TaskActivity.this, "Failed to delete task", Toast.LENGTH_SHORT).show();
                }

                // Change the view to MainActivity
                changeViewToMainActivity();
            } catch (Exception e) {
                // If an exception occurs, handle it here
                Toast.makeText(TaskActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void changeViewToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
