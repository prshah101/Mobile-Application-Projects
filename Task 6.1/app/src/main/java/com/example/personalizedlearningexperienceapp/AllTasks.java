package com.example.personalizedlearningexperienceapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.personalizedlearningexperienceapp.Models.QuizResponse;
import com.google.gson.Gson;

import java.util.List;

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
        allTasksTitle3 = findViewById(R.id.allTasksTitle3);

        // Retrieve selected news ID and source information from SignUp page
        String username = getIntent().getStringExtra("username");
        allTasksTitle2.setText(username);

        fetchTriviaQuestions();
    }


    private void fetchTriviaQuestions() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://opentdb.com/api.php?amount=10&category=18&type=multiple";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("MainActivity", "Response is: " + response);
                    QuizResponse quizResponse = new Gson().fromJson(response, QuizResponse.class);
                    List<QuizResponse.QuizResults> todoItems = quizResponse.getResults();

                    RecyclerView recyclerView = findViewById(R.id.rv2);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));

                    MyAdapter adapter = new MyAdapter(todoItems);
                    recyclerView.setAdapter(adapter);
                }, error -> Log.e("MainActivity", "That didn't work", error));

        queue.add(stringRequest);
    }

}
