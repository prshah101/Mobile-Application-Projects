package com.example.chatbot;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Chat extends AppCompatActivity {
    Button sendButton;
    EditText inputMessageEditText;
    LinearLayout chatContainer;

    JSONArray chatHistory = new JSONArray();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendButton = findViewById(R.id.sendButton);
        inputMessageEditText = findViewById(R.id.inputMessageEditText);
        chatContainer = findViewById(R.id.chatContainer);

        sendButton.setOnClickListener(v -> {
            String userMessage = inputMessageEditText.getText().toString();
            sendMessageToAPI(userMessage);
            inputMessageEditText.getText().clear();

        });
    }

    private void sendMessageToAPI(String userMessage) {
        String url = "http://10.0.2.2:5000/chat";

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("userMessage", userMessage);
            requestBody.put("chatHistory", new JSONArray(chatHistory.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("MainActivity", "Request Body: " + requestBody.toString());

        sendMessage(userMessage, "", "user");
         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String botMessage = response.getString("message");
                            sendMessage(userMessage, botMessage, "bot");

                            JSONObject chat = new JSONObject();

                            try {
                                chat.put("name", "User");
                                chat.put("name", "Llama");
                                chatHistory.put(chat);

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    private void sendMessage(String originalMessage, String message, String type) {
        if (type.equals("user")) {
            RelativeLayout userBubbleView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.user_chat_bubble, chatContainer, false);
            TextView userMessageTextView = userBubbleView.findViewById(R.id.userMessageTextView);
            userMessageTextView.setText(originalMessage);

            chatContainer.addView(userBubbleView);
        } else if (type.equals("bot")) {
            RelativeLayout botBubbleView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.bot_chat_bubble, chatContainer, false);
            TextView botMessageTextView = botBubbleView.findViewById(R.id.botMessageTextView);
            botMessageTextView.setText(message);

            chatContainer.addView(botBubbleView);
        }
    }
}