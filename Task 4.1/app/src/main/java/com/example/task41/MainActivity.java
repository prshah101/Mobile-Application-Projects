package com.example.task41;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import java.util.List;

//The inital page the user lands on
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView taskTitle;
    TextView noTasksWarningTextView;
    Button addTaskBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        taskTitle = findViewById(R.id.appTitle);
        noTasksWarningTextView = findViewById(R.id.noTasksWarning);
        noTasksWarningTextView.setVisibility(View.GONE);
        addTaskBtn = findViewById(R.id.addTaskBtn);
        DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);

        // Find RecyclerView in layout
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Retrieve and Print Task details
        ShowTasksOnListView();

        // Set OnClickListener for the add Task button
        addTaskBtn.setOnClickListener(this);
    }

    private void ShowTasksOnListView() {
        // Initialize Database Helper
        DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);

        //Create a list to store all the tasks in the database
        List<ToDoItem> tasks = dataBaseHelper.getAll();

        // Find RecyclerView in layout
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Creating a VerticalAdapter (which is a class created in this project) with tasks data and setting item click listener
        VerticalAdapter adapter = new VerticalAdapter(tasks, new onItemClickListener() {
            @Override
            public void itemClick(ToDoItem task) {
                // When a task item / to do item is clicked, navigate to TaskActivity.java and pass item details as extras
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                intent.putExtra("selectedItemTitle", task.getTitle());
                intent.putExtra("selectedItemDescription", task.getDescription());
                intent.putExtra("selectedItemDueDate", task.getDueDate());
                intent.putExtra("itemSelected", true);
                startActivity(intent);
            }
        });

        // Setting the layout manager and adapter for the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Toggle visibility of noTasksWarningTextView based on if tasks list is empty or not
        if (tasks.isEmpty()) {
            noTasksWarningTextView.setVisibility(View.VISIBLE);
        } else {
            noTasksWarningTextView.setVisibility(View.GONE);
        }
    }

    // Set on Click listener
    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;

        //If addTaskBtn is clicked, navigate to TaskActivity
        if (clickedButton.getId() == R.id.addTaskBtn) {
            Intent intent = new Intent(this, TaskActivity.class);
            startActivity(intent);
        }
    }
}