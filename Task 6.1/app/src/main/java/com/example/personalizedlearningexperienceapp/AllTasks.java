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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.personalizedlearningexperienceapp.Models.QuizResponse;
import com.example.personalizedlearningexperienceapp.Models.Todo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.OkHttpClient;
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


    TextView textView;

    String url = "https://www.google.com";
    String arrayUrl = "https://jsonplaceholder.typicode.com/todos";
    String objectUrl = "https://jsonplaceholder.typicode.com/todos/3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_tasks);

        allTasksTitle1 = findViewById(R.id.allTasksTitle1);
        allTasksTitle2 = findViewById(R.id.allTasksTitle2);
        //allTasksTitle3 = findViewById(R.id.allTasksTitle3);
        textView = findViewById(R.id.allTasksTitle3);

        listView = findViewById(R.id.todosList);
        //ArrayAdapter<Todo> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        // Retrieve selected news ID and source information from SignUp page
        String username = getIntent().getStringExtra("username");
        allTasksTitle2.setText(username);

        //fetchData();

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                objectUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            textView.setText(response.getString("title"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

//    private void fetchData() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://dummyjson.com/todos")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(new OkHttpClient.Builder().readTimeout(10, java.util.concurrent.TimeUnit.MINUTES).build()) // this will set the read timeout for 10mins (IMPORTANT: If not your request will exceed the default read timeout)
//                .build();
//
//
//        TodoRequest request = retrofit.create(TodoRequest.class);
//        request.getTodos().enqueue(new Callback<TodoResponse>() {
//            @Override
//            public void onResponse(Call<TodoResponse> call, Response<TodoResponse> response) {
//                if (response.isSuccessful()) {
//                    TodoResponse todoResponse = response.body();
//                    if (todoResponse != null) {
//                        List<TodoResponse.TodoItem> todoItems = todoResponse.getTodos();
//
//                        RecyclerView recyclerView = findViewById(R.id.rv2);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(AllTasks.this));
//
//                        MyAdapter adapter = new MyAdapter(todoItems);
//                        recyclerView.setAdapter(adapter);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TodoResponse> call, Throwable t) {
//                Toast.makeText(AllTasks.this, "Error fetching data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
