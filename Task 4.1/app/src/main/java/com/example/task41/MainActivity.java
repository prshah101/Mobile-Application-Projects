package com.example.task41;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView taskTitle;
    TextView noTasksWarningTextView;
    Button addTaskBtn;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize UI elements
        taskTitle = findViewById(R.id.appTitle);
        noTasksWarningTextView = findViewById(R.id.noTasksWarning);
        addTaskBtn = findViewById(R.id.addTaskBtn);

        // Example task details

        // Initialize Database Helper
        dbHelper = new DatabaseHelper(this);


        // Set OnClickListener for the button
        addTaskBtn.setOnClickListener(this);

    }

    // Set click listener for addTaskBtn if needed
    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.addTaskBtn){
            Intent intent = new Intent(this, TaskActivity.class);
            startActivity(intent);
        }
    }
}