package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalizedlearningexperienceapp.Models.QuizResponse;

import java.util.ArrayList;

public class DoQuiz extends AppCompatActivity {
    TextView allTasksTitle1;
    TextView allTasksTitle2;
    TextView allTasksTitle3;

    ListView listView;


    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doquiz);

        allTasksTitle1 = findViewById(R.id.allTasksTitle1);
        allTasksTitle2 = findViewById(R.id.allTasksTitle2);
        allTasksTitle3 = findViewById(R.id.allTasksTitle3);



        // Retrieve the Intent
        Intent intent = getIntent();
        // Check if the Intent contains extra data with the specified key
        // Retrieve the ParcelableArrayList extra data
        ArrayList<QuizResponse.QuizResults> dataList = (ArrayList<QuizResponse.QuizResults>) intent.getSerializableExtra("selectedTopicData");
        allTasksTitle1.setText("Generated Task " + 1);
        allTasksTitle2.setText(String.format("About %s", dataList.get(1).getCategory()));

        //Toast.makeText(this, dataList.get(1).getQuestion(), Toast.LENGTH_LONG).show();
        RecyclerView recyclerView = findViewById(R.id.rv3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        QuizAdapter adapter = new QuizAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }
}
