package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity2 extends AppCompatActivity {
    TextView questionHeader;
    TextView answer1;
    TextView answer2;
    TextView answer3;
    TextView questionNumber;
    TextView totalScore;
    TextView userName;
    RadioGroup radioGroup;
    CardView questionsCardView;
    //Quiz[] questions;
    int score = 0;
    ProgressBar progressBar;
    int currentQuestion = 0;
    Quiz quiz;

    Button nextButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        quiz = Quiz.getInstance();

        questionHeader = findViewById(R.id.question);
        totalScore = findViewById(R.id.score);

        answer1 = findViewById(R.id.option1);
        answer2 = findViewById(R.id.option2);
        answer3 = findViewById(R.id.option3);
        progressBar = findViewById(R.id.progressBar);
        questionNumber = findViewById(R.id.questionNumber);
        nextButton = findViewById(R.id.nextBtn);
        userName = findViewById(R.id.username);

        radioGroup = findViewById(R.id.radioGroup);
        questionsCardView = findViewById(R.id.optionCard);

        String name = getIntent().getStringExtra("userName");
        //To display the correct name
        userName.setText("Welcome " + User.getInstance().getName());


        handleQuiz();
    }

    private void handleQuiz() {
        displayQuestion(quiz); //1 of 5
    }

    public void onStartButtonClick(View view) {
        changeQuestion(quiz);
        currentQuestion++;
        displayQuestion(quiz); //2 of 5
    }

    private void changeQuestion(Quiz quiz) {
        // Change the question, answers, and correct answer dynamically
        quiz.setQuestion("What is the capital of France?");
        quiz.setAnswers(new String[]{"London", "Paris", "Berlin"});
        quiz.setCorrectAnswer(1);
    }

    private void displayQuestion(Quiz quiz) {
        // Display the current question
        questionHeader.setText( quiz.getQuestion());
        // Display the answer options
        String[] answers = quiz.getAnswers();
        answer1.setText(answers[0]);
        answer2.setText(answers[1]);
        answer3.setText(answers[2]);
    }

}
