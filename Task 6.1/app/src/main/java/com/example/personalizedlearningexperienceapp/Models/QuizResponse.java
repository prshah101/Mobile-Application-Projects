package com.example.personalizedlearningexperienceapp.Models;

import java.util.List;

public class QuizResponse {
    private List<QuizQuestion> quiz;

    // Constructor
    public QuizResponse() {
    }

    // Getter and Setter for quiz
    public List<QuizQuestion> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<QuizQuestion> quiz) {
        this.quiz = quiz;
    }
}
