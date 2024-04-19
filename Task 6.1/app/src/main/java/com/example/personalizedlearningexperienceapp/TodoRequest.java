package com.example.personalizedlearningexperienceapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoRequest {
    @GET("todos")
    Call<TodoResponse> getTodos();
//
//    @POST("endpoint")
//    Call<Void> sendData(@Body MyData data);


}

