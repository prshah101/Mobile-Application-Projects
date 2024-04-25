package com.example.personalizedlearningexperienceapp;
import com.example.personalizedlearningexperienceapp.Models.QuizResponse;
import com.example.personalizedlearningexperienceapp.Models.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("getQuiz?topic=Android%20Development")
    Call<QuizResponse> getQuiz();
}
