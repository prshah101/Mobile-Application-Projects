package com.example.personalizedlearningexperienceapp;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.personalizedlearningexperienceapp.TopicAdapter;

import java.util.ArrayList;
import java.util.List;

public class SignUpInterestActivity extends AppCompatActivity {
    private RecyclerView rv1;
    private Button nextButton;
    private List<String> interestsList;
    private TopicAdapter topicsAdapter;

    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_interests);

        rv1 = findViewById(R.id.rv1);
        nextButton = findViewById(R.id.nextBtn);

        // Get the data from DataSource class for topics
        ArrayList<String> topics1 = DataSource.geTopics1();
        ArrayList<String> topics2 = DataSource.geTopics2();

        // Initialize and set up the adapter for top stories RecyclerView
        linearLayoutManager = new LinearLayoutManager(SignUpInterestActivity.this, LinearLayoutManager.VERTICAL, false);
        topicsAdapter = new TopicAdapter(topics1, topics2);
        rv1.setLayoutManager(linearLayoutManager);
        rv1.setAdapter(topicsAdapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpInterestActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
