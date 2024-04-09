package com.example.newsapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;

    ArrayList<String> titleDataSource;
    ArrayList<Integer> imageDataSource;
    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rv = findViewById(R.id.topStoriesHorizontalRV);

        //Setting the data source
        titleDataSource = new ArrayList<>();
        titleDataSource.add("WBS Stock Falling");
        titleDataSource.add("Senetor Announces Plans");
        titleDataSource.add("New Law Passed");
        titleDataSource.add("Team at Championship");

        imageDataSource = new ArrayList<>();
        imageDataSource.add(R.drawable.top1);
        imageDataSource.add(R.drawable.top2);
        imageDataSource.add(R.drawable.top3);
        imageDataSource.add(R.drawable.top4);

        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        myRvAdapter = new MyRvAdapter(titleDataSource, imageDataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);
    }
}