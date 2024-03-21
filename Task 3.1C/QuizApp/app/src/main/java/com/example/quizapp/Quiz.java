package com.example.quizapp;

public class Quiz { // This class contains the set of quiz questions and answers

    // Array to store the quiz questions
    public static String question[] = {
            "How many continents are there?",
            "What's the capital of Australia?",
            "How many heart(s) does a human have?",
            "Finish the sequence, North, East, South, ____?",
            "Who's the Prime Minister of Australia?"
    };

    // 2D array to store the choices for each question //3 columns by 5 rows
    public static String choices[][] = {
            {"5", "7", "10"},
            {"Melbourne", "Sydney", "Canberra"},
            {"3", "1", "2"},
            {"West", "Pest", "Crest"},
            {"Kevin Rudd", "Anthony Albanese", "Julia Roberts"}
    };

    // Array to store the correct answer for each question
    public static String correctAnswer[] = {
            "7",
            "Canberra",
            "1",
            "West",
            "Anthony Albanese"

    };
}