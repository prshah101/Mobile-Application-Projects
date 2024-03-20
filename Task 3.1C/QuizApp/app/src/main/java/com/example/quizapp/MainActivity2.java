package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity2 extends AppCompatActivity  implements View.OnClickListener{
    TextView questionHeader;
    TextView answer1;
    TextView answer2;
    TextView answer3;
    TextView questionNumber;
    String selectedAnswer = "";
    TextView totalScore;
    int totalQuestions = 5;
    Button previousClickedButton = null;
    TextView userName;
    RadioGroup radioGroup;
    CardView questionsCardView;
    //Quiz[] questions;
    int score = 0;
    ProgressBar progressBar;
    int currentQuestion = 0;
    //Quiz quiz;

    Button nextButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //quiz = Quiz.getInstance();

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
        totalScore.setText("Score " + score);

        // Set OnClickListener
        nextButton.setOnClickListener(this);
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);


        handleQuiz();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {

        //Default is White
        resetButtonColor();

        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.nextBtn) {  // Logic for handling "Next" button click
            if(selectedAnswer.equals(Quiz.correctAnswer[currentQuestion])){
                score++;
                previousClickedButton.setBackgroundColor(Color.GREEN);
            }
            else{
                previousClickedButton.setBackgroundColor(Color.RED);
                // Set the correct answer button to green//To indicate correct answer
                if (answer1.getText().toString().equals(Quiz.correctAnswer[currentQuestion])) {
                    answer1.setBackgroundColor(Color.GREEN);
                } else if (answer2.getText().toString().equals(Quiz.correctAnswer[currentQuestion])) {
                    answer2.setBackgroundColor(Color.GREEN);
                } else if (answer3.getText().toString().equals(Quiz.correctAnswer[currentQuestion])) {
                    answer3.setBackgroundColor(Color.GREEN);
                }
            }
            //resetButtonColor();
            //currentQuestion++;
            //handleQuiz();
        } else {
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.LTGRAY);
            previousClickedButton = clickedButton;
        }
    }

    private void resetButtonColor(){
        answer1.setBackgroundColor(Color.WHITE);
        answer2.setBackgroundColor(Color.WHITE);
        answer3.setBackgroundColor(Color.WHITE);
    }
    private void handleQuiz() {
        if(currentQuestion == totalQuestions ){
            quizFinished();
            return;
        }

        questionHeader.setText(Quiz.question[currentQuestion]);
        answer1.setText(Quiz.choices[currentQuestion][0]);
        answer2.setText(Quiz.choices[currentQuestion][1]);
        answer3.setText(Quiz.choices[currentQuestion][2]);
    }

    void quizFinished(){

    }

}
