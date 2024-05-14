package com.example.chatbot;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;

import okhttp3.OkHttpClient;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Chat extends AppCompatActivity {
    private Button loginBtn;
    private EditText usernameEnter;

    TextView chatTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);

        chatTv = findViewById(R.id.chatTv);

        String userMessage = "Give me instructions to cook a pizza";

        // Define an ArrayList to hold chat history entries
        ArrayList<ChatResponse.ChatHistory> chatHistory = new ArrayList<>();

        // Create a new chat entry and add it to the chat history
        ChatResponse.ChatHistory entry = new ChatResponse.ChatHistory("Hello", "Hello! Welcome to the chat! How can I assist you today? Is there something specific you would like to talk about or ask?");
        chatHistory.add(entry);

        //postChatDetails(userMessage, chatHistory);

        fetchChatDetails();
    }

    private void fetchChatDetails() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://127.0.0.1:5000/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("Chat", "Response is: " + response);
                    //ChatResponse quizResponse = new Gson().fromJson(response, ChatResponse.class);

                    chatTv.setText(response);
                }, error -> Log.e("Chat", "That didn't work", error));

        queue.add(stringRequest);

    }

    private OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .build();
    }

    private void postChatDetails(String userMessage, ArrayList<ChatResponse.ChatHistory> chatHistory) {
        String postUrl = "http://127.0.0.1:5000/chat";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();
        try {
            postData.put("userMessage", userMessage);
            postData.put("chatHistory", chatHistory);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(Chat.this, "1", Toast.LENGTH_SHORT).show();
        //System.out.println("response");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                Toast.makeText(Chat.this, "2", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(Chat.this, "3", Toast.LENGTH_SHORT).show();
            }
        });

        // Set the timeout to 10 minutes (600,000 milliseconds)
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                600000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonObjectRequest);
    }
}