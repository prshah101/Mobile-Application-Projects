package com.example.personalizedlearningexperienceapp;

public class Scores {
    private String username;
    private int totalScore;
    private int correctScore;
    private int wrongScore;

    public Scores(String username, int totalScore, int correctScore, int wrongScore) {
        this.username = username;
        this.totalScore = totalScore;
        this.correctScore = correctScore;
        this.wrongScore = wrongScore;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getCorrectScore() {
        return correctScore;
    }

    public void setCorrectScore(int correctScore) {
        this.correctScore = correctScore;
    }

    public int getWrongScore() {
        return wrongScore;
    }

    public void setWrongScore(int wrongScore) {
        this.wrongScore = wrongScore;
    }
}