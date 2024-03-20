package com.example.quizapp;

public class Quiz {

    private String question;
    private String[] answers;
    private int correctAnswer;

    private static Quiz instance;

    public static Quiz getInstance() {
        if (instance == null) {
            instance = new Quiz("How many continents are there?",
                    new String[] {"5", "7", "10", "2"},
                    1);
        }
        return instance;
    }

    public Quiz(String question, String[] answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getcorrectAnswer() { return correctAnswer;}

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}