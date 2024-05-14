package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalizedlearningexperienceapp.Models.QuizResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuizResults extends AppCompatActivity {
    TextView questionResultsTv;
    TextView questionResultsTv2;
    TextView textChosen;
    TextView textChosen2;

    TextView explainTv;
    TextView explainTv2;



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


        String question1 = getIntent().getStringExtra("question1");
        String question1correctanswer = getIntent().getStringExtra("question1correctanswer");
        String choice1 = getIntent().getStringExtra("choice1");
        String question2 = getIntent().getStringExtra("question1");
        String question2correctanswer = getIntent().getStringExtra("question2correctanswer");
        String choice2 = getIntent().getStringExtra("choice2");

        if (!choice1.equals(question1correctanswer)){
            textChosen.setTextColor(Color.RED);
            textChosen.setText("Incorrect!");

            explainTv.setVisibility(View.VISIBLE);
            explainTv.setText("For '" + question1 +"' the correct answer was '" + question1correctanswer
            + "' but you chose '" + choice1 +"'");
        }else{
            textChosen.setTextColor(Color.GREEN);
            textChosen.setText("Correct!");
        }

        if (!choice2.equals(question2correctanswer)){
            textChosen2.setTextColor(Color.RED);
            textChosen2.setText("Incorrect!");

            explainTv2.setVisibility(View.VISIBLE);
            explainTv2.setText("For '" + question2 +"' the correct answer was '" + question2correctanswer
                    + "' but you chose '" + choice2 +"'");
        }else
        {
            textChosen2.setTextColor(Color.GREEN);
            textChosen2.setText("Correct!");
        }

    }
}
