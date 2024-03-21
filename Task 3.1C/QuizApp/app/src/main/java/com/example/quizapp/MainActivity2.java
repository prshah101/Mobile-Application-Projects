package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    int totalQuestions = Quiz.question.length;
    Button previousClickedButton = null;
    TextView userName;
    TextView congratulationView;
    TextView scoreView;
    TextView numericScoreView;
    RadioGroup radioGroup;
    CardView questionsCardView;
    int score = 0;
    ProgressBar progressBar;
    int currentQuestion = 0;

    Button nextButton;
    Button submitButton;

    Button restartButton;
    Button finishButton;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        questionHeader = findViewById(R.id.question);
        answer1 = findViewById(R.id.option1);
        answer2 = findViewById(R.id.option2);
        answer3 = findViewById(R.id.option3);
        progressBar = findViewById(R.id.progressBar);
        questionNumber = findViewById(R.id.questionNumber);
        nextButton = findViewById(R.id.nextBtn);
        submitButton = findViewById(R.id.submitBtn);
        userName = findViewById(R.id.username);
        radioGroup = findViewById(R.id.radioGroup);
        questionsCardView = findViewById(R.id.optionCard);

        ///For 'View 3'///
        restartButton = findViewById(R.id.restartBtn);
        finishButton = findViewById(R.id.finishBtn);
        congratulationView = findViewById(R.id.congratsView);
        congratulationView.setText("Congratulations " + User.getInstance().getName() + " !");
        scoreView = findViewById(R.id.scoreView);
        numericScoreView = findViewById(R.id.numericScoreView);
        restartButton.setVisibility(View.GONE);
        finishButton.setVisibility(View.GONE);
        congratulationView.setVisibility(View.GONE);
        scoreView.setVisibility(View.GONE);
        numericScoreView.setVisibility(View.GONE);

        String name = getIntent().getStringExtra("userName");
        //To display the correct name
        userName.setText("Welcome " + User.getInstance().getName());


        // Set OnClickListener
        nextButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        nextButton.setVisibility(View.INVISIBLE);


        handleQuiz();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {

        //Default is White
        resetButtonColor();

        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.submitBtn) {  // Logic for handling "Next" button click
            if (selectedAnswer.equals(Quiz.correctAnswer[currentQuestion])) {
                score++;
                previousClickedButton.setBackgroundColor(Color.GREEN);
            } else {
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

            // Hide submit button, show next button
            submitButton.setVisibility(View.INVISIBLE);
            nextButton.setVisibility(View.VISIBLE);
        } else if (clickedButton.getId() == R.id.nextBtn) {
            // Logic for handling "Next" button click
            currentQuestion++;
            handleQuiz();
            // Hide next button again
            nextButton.setVisibility(View.INVISIBLE);
            submitButton.setVisibility(View.VISIBLE);
            if (currentQuestion == totalQuestions) { //After handleQuiz for the final time, help remove all elements
                submitButton.setVisibility(View.GONE);
                nextButton.setVisibility(View.GONE);
                congratulationView.setVisibility(View.VISIBLE);
                restartButton.setVisibility(View.VISIBLE);
                finishButton.setVisibility(View.VISIBLE);
                scoreView.setVisibility(View.VISIBLE);
                numericScoreView.setVisibility(View.VISIBLE);
                numericScoreView.setText(score + "/" + totalQuestions);
            }
        }else if (clickedButton.getId() == R.id.restartBtn){
            restartQuiz();
        }else if (clickedButton.getId() == R.id.finishBtn){
            finish();
        }else {
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
        // Update the question number on the screen
        questionNumber.setText((currentQuestion + 1) + "/" + totalQuestions);

        // Calculate progress percentage
        int progress = (int) (((float) (currentQuestion) / totalQuestions) * 100);

        // Set the progress bar
        progressBar.setProgress(progress);

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
        LinearLayout layout = findViewById(R.id.layoutID);

        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            child.setVisibility(View.GONE);
        }


    }

    void restartQuiz(){
        score = 0;
        currentQuestion =0;
        handleQuiz();
    }

}
