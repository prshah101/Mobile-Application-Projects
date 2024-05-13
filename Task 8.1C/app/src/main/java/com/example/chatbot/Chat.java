package com.example.chatbot;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().readTimeout(10, java.util.concurrent.TimeUnit.MINUTES).build()) // this will set the read timeout for 10mins (IMPORTANT: If not your request will exceed the default read timeout)
                .build();

        ToChatRequest request = retrofit.create(ToChatRequest.class);

        //Call<Void> call = ToChatRequest.postData(requestBody);
        //Call<ChatResponse> call = ToChatRequest.getData();
        call.enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.isSuccessful()) {
                    ChatResponse data = response.body();
                    // Do something with the data
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                // Handle failure
                Log.e("Chat.java", "Failed to Fetch Data", t);
            }
        });

    }
}