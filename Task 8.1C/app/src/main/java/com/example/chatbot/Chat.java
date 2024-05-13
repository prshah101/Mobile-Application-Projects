package com.example.chatbot;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;

import okhttp3.OkHttpClient;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;

public class Chat extends AppCompatActivity {
    private Button loginBtn;
    private EditText usernameEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);

        fetchChatDetails();
    }

    private void fetchChatDetails() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://opentdb.com/api.php?amount=3&category=18&type=multiple";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("MainActivity", "Response is: " + response);
                    ChatResponse quizResponse = new Gson().fromJson(response, ChatResponse.class);
                    List<ChatResponse.ChatHistory> todoTasks = quizResponse.getChatHistory();

                    RecyclerView recyclerView = findViewById(R.id.rv2);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));

                    ChatAdapter adapter = new ChatAdapter(todoTasks);
                    recyclerView.setAdapter(adapter);
                }, error -> Log.e("MainActivity", "That didn't work", error));

        queue.add(stringRequest);

    }
}