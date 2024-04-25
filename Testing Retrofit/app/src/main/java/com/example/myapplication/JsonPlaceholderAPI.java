package com.example.myapplication;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderAPI {
    @GET("/posts")
    Call<List<Post>> getPosts();
}
