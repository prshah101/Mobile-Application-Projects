package com.example.chatbot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Chat extends AppCompatActivity {
    ImageButton sendBtn;
    EditText inputMessageEt;

    LinearLayout chatLayout;

    JSONArray chatHistory = new JSONArray();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendBtn = findViewById(R.id.sendBtn);
        inputMessageEt = findViewById(R.id.inputMessageEt);
        chatLayout = findViewById(R.id.chatLayout);

        sendBtn.setOnClickListener(v -> {
            String userMessage = inputMessageEt.getText().toString();
            sendMessageToAPI(userMessage);
            inputMessageEt.getText().clear();

        });

        RelativeLayout llamaChatView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.llama_chat_box, chatLayout, false);
        TextView llamaMessageTv = llamaChatView.findViewById(R.id.llamaMessageTextView);
        llamaMessageTv.setText("Welcome User");

        chatLayout.addView(llamaChatView);
    }

    private void sendMessageToAPI(String userMessage) {
        String url = "http://api.sheronsuditha.me:5051/chat";

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("userMessage", userMessage);
            requestBody.put("chatHistory", new JSONArray(chatHistory.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        displayMessage(userMessage, "", "user");
         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String llamaMessage = response.getString("message");
                            displayMessage(userMessage, llamaMessage, "llama");

                            JSONObject chat = new JSONObject();

                            try {
                                chat.put("User", userMessage);
                                chat.put("Llama", llamaMessage);
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

    private void displayMessage(String originalMessage, String message, String type) {
        if (Objects.equals(type, "user")) {
            RelativeLayout userChatView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.user_chat_box, chatLayout, false);
            TextView userMessageTv = userChatView.findViewById(R.id.userMessageTextView);
            userMessageTv.setText(originalMessage);

            chatLayout.addView(userChatView);
        } else if (Objects.equals(type, "llama")) {
            RelativeLayout llamaChatView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.llama_chat_box, chatLayout, false);
            TextView llamaMessageTv = llamaChatView.findViewById(R.id.llamaMessageTextView);
            llamaMessageTv.setText(message);

            chatLayout.addView(llamaChatView);
        }
    }
}