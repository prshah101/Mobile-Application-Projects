package com.example.personalizedlearningexperienceapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllTasks extends AppCompatActivity {

    TextView allTasksTitle1;
    TextView allTasksTitle2;
    TextView allTasksTitle3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_tasks);

        allTasksTitle1 = findViewById(R.id.allTasksTitle1);
        allTasksTitle2 = findViewById(R.id.allTasksTitle2);
        allTasksTitle3 = findViewById(R.id.allTasksTitle3);
        RecyclerView recyclerView = findViewById(R.id.rv2);

        // Retrieve selected news ID and source information from SignUp page
        String username = getIntent().getStringExtra("username");
        allTasksTitle2.setText(username);

        fetchData();
    }

    private void fetchData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dunnyison.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TodoRequest request = retrofit.create(TodoRequest.class);

        //Toast.makeText(getApplicationContext(), "Working", Toast.LENGTH_SHORT).show();
        request.getTodos().enqueue(new Callback<TodoResponse>() {
            @Override
            public void onResponse(Call<TodoResponse> call, Response<TodoResponse> response) {
                //Toast.makeText(getApplicationContext(), "Working2", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    TodoResponse todoResponse = response.body();
                    if (todoResponse != null) {
                        List<TodoItem> todoItems = todoResponse.getTodos();

                        RecyclerView recyclerView = findViewById(R.id.rv2);
                        recyclerView.setLayoutManager(new LinearLayoutManager(AllTasks.this));

//                        MyAdapter adapter = new MyAdapter(todoItems);
//                        recyclerView.setAdapter(adapter);
//                        for (TodoResponse.TodoItem item : todoItems) {
//                            Toast.makeText(AllTasks.this, item.getTodo(), Toast.LENGTH_SHORT).show();
//                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<TodoResponse> call, Throwable t) {
                Log.e("All Tasks", "Failed to fetch data", t);
            }
        });
    }
}
