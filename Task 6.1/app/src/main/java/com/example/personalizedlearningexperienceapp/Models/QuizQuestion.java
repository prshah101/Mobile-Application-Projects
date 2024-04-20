package com.example.personalizedlearningexperienceapp.Models;

import java.util.List;

public class QuizQuestion {
    private String correct_answer;
    private List<String> options;
    private String question;

    // Constructor
    public QuizQuestion() {
    }

    // Getters
    public String getCorrect_answer() {
        return correct_answer;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getQuestion() {
        return question;
    }

    // Setters
    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
