package com.example.personalizedlearningexperienceapp.Models;

import java.io.Serializable;
import java.util.List;

public class QuizResponse {
    private List<QuizResults> results;

    public List<QuizResults> getResults() {
        return results;
    }

    public static class QuizResults implements Serializable {
        private String type;
        private String difficulty;
        private String category;
        private String question;
        private String correct_answer;
        private List<String> incorrect_answers;


        public String getType() {
            return type;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public String getCategory() {
            return category;
        }

        public String getQuestion() {
            return question;
        }

        public String getCorrect_answer() {
            return correct_answer;
        }

        public List<String> getIncorrect_answers() {
            return incorrect_answers;
        }

    }

}