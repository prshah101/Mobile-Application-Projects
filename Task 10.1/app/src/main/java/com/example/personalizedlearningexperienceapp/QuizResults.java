package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizResults extends AppCompatActivity {
    TextView questionResultsTv;
    TextView questionResultsTv2;
    TextView textChosen;
    TextView textChosen2;
    TextView explainTv;
    TextView explainTv2;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_results);

        questionResultsTv = findViewById(R.id.questionResultsTv);
        questionResultsTv2 = findViewById(R.id.questionResultsTv2);
        textChosen = findViewById(R.id.textChosen);
        textChosen2 = findViewById(R.id.textChosen2);
        explainTv = findViewById(R.id.explainTv);
        explainTv2 = findViewById(R.id.explainTv2);
        explainTv.setVisibility(View.INVISIBLE);
        explainTv2.setVisibility(View.INVISIBLE);

        databaseHelper = new DatabaseHelper(this); // Initialize DatabaseHelper
        Intent intent = getIntent();
        String question1 = intent.getStringExtra("question1");
        String question1correctanswer = intent.getStringExtra("question1correctanswer");
        String choice1 = intent.getStringExtra("choice1");
        String question2 = intent.getStringExtra("question2");
        String question2correctanswer = intent.getStringExtra("question2correctanswer");
        String choice2 = intent.getStringExtra("choice2");
        String username = intent.getStringExtra("username");

        Scores scores = databaseHelper.getScoresByUsername(username);
        int totalScore = 0;
        int correctScore = scores.getCorrectScore();
        int wrongScore = scores.getWrongScore();

        if (!choice1.equals(question1correctanswer)) {
            textChosen.setTextColor(Color.RED);
            textChosen.setText("Incorrect!");

            explainTv.setVisibility(View.VISIBLE);
            explainTv.setText("For '" + question1 + "' the correct answer was '" + question1correctanswer
                    + "' but you chose '" + choice1 + "'");

            wrongScore++;
        } else {
            textChosen.setTextColor(Color.GREEN);
            textChosen.setText("Correct!");
            correctScore++;
        }

        if (!choice2.equals(question2correctanswer)) {
            textChosen2.setTextColor(Color.RED);
            textChosen2.setText("Incorrect!");

            explainTv2.setVisibility(View.VISIBLE);
            explainTv2.setText("For '" + question2 + "' the correct answer was '" + question2correctanswer
                    + "' but you chose '" + choice2 + "'");

            wrongScore++;
        } else {
            textChosen2.setTextColor(Color.GREEN);
            textChosen2.setText("Correct!");
            correctScore++;
        }

        totalScore = correctScore + wrongScore;

        // Store the scores in the database
        boolean isScoreAdded = databaseHelper.addOrUpdateScores(username, totalScore, correctScore, wrongScore);
        if (isScoreAdded) {
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Added", Toast.LENGTH_SHORT).show();
        }
    }
}
