package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
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

public class QuizResults extends AppCompatActivity {
    TextView questionResultsTv;
    TextView questionResultsTv2;
    TextView textChosen;
    TextView textChosen2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_results);

        questionResultsTv = findViewById(R.id.questionResultsTv);
        questionResultsTv2 = findViewById(R.id.questionResultsTv2);
        textChosen = findViewById(R.id.textChosen);
        textChosen2 = findViewById(R.id.textChosen2);


        String choice1 = getIntent().getStringExtra("choice1");
        String choice2 = getIntent().getStringExtra("choice2");
        textChosen.setText(choice1);
        textChosen2.setText(choice2);

    }
}
