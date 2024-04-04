package com.example.task41;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView taskTitle;
    TextView noTasksWarningTextView;
    Button addTaskBtn;
    ListView todolist;
    RecyclerView RecyclerViewMain;
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize UI elements
        taskTitle = findViewById(R.id.appTitle);
        noTasksWarningTextView = findViewById(R.id.noTasksWarning);
        noTasksWarningTextView.setVisibility(View.GONE);
        addTaskBtn = findViewById(R.id.addTaskBtn);
        //RecyclerViewMain = findViewById(R.id.RecyclerView);
        DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);

        //todolist = findViewById(R.id.todolist);
        // Find RecyclerView in layout
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Example task details


        ShowTasksOnListView();

        // Set OnClickListener for the button
        addTaskBtn.setOnClickListener(this);
    }

    private void ShowTasksOnListView() {
        // Initialize Database Helper
        DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);

        List<ToDoItem> tasks = dataBaseHelper.getAll();

        // Find RecyclerView in layout
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        VerticalAdapter adapter = new VerticalAdapter(tasks, new onItemClickListener() {
            @Override
            public void itemClick(ToDoItem task) {
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                intent.putExtra("selectedItemTitle", task.getTitle());
                intent.putExtra("selectedItemDescription", task.getDescription());
                intent.putExtra("selectedItemDueDate", task.getDueDate());
                intent.putExtra("itemSelected", true);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Toggle visibility of noTasksWarningTextView
        if (tasks.isEmpty()) {
            noTasksWarningTextView.setVisibility(View.VISIBLE);
        } else {
            noTasksWarningTextView.setVisibility(View.GONE);
        }
    }

    // Set click listener for addTaskBtn if needed
    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.addTaskBtn) {
            Intent intent = new Intent(this, TaskActivity.class);
            startActivity(intent);
        }
    }
}