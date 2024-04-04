package com.example.task41;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;

//The second page the user navigates to
public class TaskActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    //Declare the necessary variables used throughout the class
    EditText taskTitleEditText;
    EditText descriptionEditText;

    TextView dueDateText;

    Button dueDateButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetails); // Replace "your_layout" with the actual name of your XML layout file

        // Access the EditText fields and buttons
        dueDateButton = findViewById(R.id.dueDateButton);
        dueDateText = findViewById(R.id.dueDateText);
        taskTitleEditText = findViewById(R.id.taskTitle);
        descriptionEditText = findViewById(R.id.description);
        Button editSaveBtn = findViewById(R.id.editSaveBtn);
        Button deleteTaskBtn = findViewById(R.id.deleteTaskBtn);
        Button backtoMainBtn = findViewById(R.id.backBtn);

        // Retrieve the values passed from MainActivity
        String selectedItemTitle = getIntent().getStringExtra("selectedItemTitle");
        String selectedItemDescription = getIntent().getStringExtra("selectedItemDescription");
        String selectedItemDueDate = getIntent().getStringExtra("selectedItemDueDate");
        //Set the values retrieved from MainActivity
        taskTitleEditText.setText(selectedItemTitle);
        dueDateText.setText(selectedItemDueDate);
        descriptionEditText.setText(selectedItemDescription);

        // Set OnClickListener for the buttons
        editSaveBtn.setOnClickListener(this);
        deleteTaskBtn.setOnClickListener(this);
        backtoMainBtn.setOnClickListener(this);
        dueDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the date picker dialog
                com.example.task41.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new com.example.task41.DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

    }

    // A method to set the selected date on the TextView for the Due Date
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

        //If editSaveBtn is pressed, and a existing ToDoItem wasn't selected, add a new ToDoItem to the database
        if (clickedButton.getId() == R.id.editSaveBtn && !itemSelected) {

            try {
                // Create a ToDoItem instance accessible in this class (not the database yet)
                ToDoItem todoItem = new ToDoItem(taskTitleEditText.getText().toString(), descriptionEditText.getText().toString(), dueDateText.getText().toString());

                //Create an instance of dataBaseHelper to access the database
                DatabaseHelper dataBaseHelper = new DatabaseHelper(TaskActivity.this);

                //Access the addOne() method in the database, to add the todoItem to the database
                boolean success = dataBaseHelper.addOne(todoItem);

                //Display the relevant Toast based on the success of the todoItem addition to the database
                if (success) {
                    Toast.makeText(TaskActivity.this, "Task added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TaskActivity.this, "Failed to add task", Toast.LENGTH_SHORT).show();
                }

                // Change the view to MainActivity
                changeViewToMainActivity();

            } catch (Exception e) {
                // If an exception occurs, handle it here
                Toast.makeText(TaskActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            //If editSaveBtn is pressed, and a existing ToDoItem was selected, update the existing ToDoItem to the database
        } else if (clickedButton.getId() == R.id.editSaveBtn && itemSelected) {
            try {
                // Create a ToDoItem instance accessible in this class (not the database yet)
                ToDoItem todoItem = new ToDoItem(taskTitleEditText.getText().toString(), descriptionEditText.getText().toString(), dueDateText.getText().toString());
                DatabaseHelper dataBaseHelper = new DatabaseHelper(TaskActivity.this);

                //Access the updateOne() method in the database, to update the todoItem in the database
                boolean success = dataBaseHelper.updateOne(todoItem);

                //Display the relevant Toast based on the success of the todoItem update in the database
                if (success) {
                    Toast.makeText(TaskActivity.this, "Task updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TaskActivity.this, "Failed to update task", Toast.LENGTH_SHORT).show();
                }

                // Change the view to MainActivity
                changeViewToMainActivity();

            } catch (Exception e) {
                // If an exception occurs, handle it here
                Toast.makeText(TaskActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (clickedButton.getId() == R.id.backBtn) {
            // Change the view to MainActivity
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

    //A method to change the view to MainActivity, since currently we're on TaskActivity
    private void changeViewToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
