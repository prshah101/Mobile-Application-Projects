package com.example.chatbot;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ToChatRequest {
    @GET("/")
    Call<ChatResponse> getData();

    @POST("/postData")
    Call<Void> postData(@Body RequestBody requestBody);
}
