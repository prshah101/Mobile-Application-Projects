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

    //ArrayAdapter<ToDoItem> taskArrayAdapter;


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

        todolist = findViewById(R.id.todolist);


        // Example task details


        ShowTasksOnListView();

        // Set OnClickListener for the button
        addTaskBtn.setOnClickListener(this);

        todolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item from the adapter
                ToDoItem selectedItem = (ToDoItem) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, TaskActivity.class);

                // Put the selected item's value as an extra in the intent
                intent.putExtra("selectedItemTitle", selectedItem.getTitle());
                intent.putExtra("selectedItemDescription", selectedItem.getDescription());
                intent.putExtra("itemSelected", true);

                startActivity(intent);


            }
        });

    }

    private void ShowTasksOnListView() {
        // Initialize Database Helper
        DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);

        //Toast.makeText(MainActivity.this, dataBaseHelper.getAll().toString(), Toast.LENGTH_SHORT).show();
        List<ToDoItem> tasks = dataBaseHelper.getAll();
        ArrayAdapter<ToDoItem> taskArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, tasks);
        todolist.setAdapter(taskArrayAdapter);
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