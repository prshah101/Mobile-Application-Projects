package com.example.personalizedlearningexperienceapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.personalizedlearningexperienceapp.Models.Todo;

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

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_tasks);

        allTasksTitle1 = findViewById(R.id.allTasksTitle1);
        allTasksTitle2 = findViewById(R.id.allTasksTitle2);
        allTasksTitle3 = findViewById(R.id.allTasksTitle3);
        RecyclerView recyclerView = findViewById(R.id.rv2);

        listView = findViewById(R.id.todosList);
        ArrayAdapter<Todo> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        // Retrieve selected news ID and source information from SignUp page
        String username = getIntent().getStringExtra("username");
        allTasksTitle2.setText(username);

        fetchData();
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Todo>> call = apiService.getTodos();
        call.enqueue(new Callback<List<Todo>>() {

            ArrayAdapter<Todo> arrayAdapter = new ArrayAdapter<>(AllTasks.this, android.R.layout.simple_list_item_1);
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                arrayAdapter.addAll(response.body());
                listView.setAdapter(arrayAdapter);
                Toast.makeText(AllTasks.this, "Todos fetched successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Toast.makeText(AllTasks.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
