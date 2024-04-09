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

    RecyclerView rv2;

    LinearLayoutManager linearLayoutManager;

    LinearLayoutManager linearLayoutManager2;
    TopStoriesAdapter topStoriesAdapter;

    NewsAdapter newsAdapter;

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

        //For Top Stories RV
        rv = findViewById(R.id.topStoriesHorizontalRV);

        // Get the data from DataSource class
        ArrayList<Integer> topStoriesIds = DataSource.getId();
        ArrayList<String> titleDataSource = DataSource.getTitleDataSource();
        ArrayList<Integer> imageDataSource = DataSource.getImageDataSource();
        ArrayList<String> getDescriptionDataSource = DataSource.getDescriptionDataSource();

        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        topStoriesAdapter = new TopStoriesAdapter(topStoriesIds, imageDataSource, titleDataSource, getDescriptionDataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(topStoriesAdapter);


        // For News RV
        rv2 = findViewById(R.id.newsHorizontalRV);

        // Get the data from DataSource class
        ArrayList<Integer> newsId = DataSource.getId();
        ArrayList<Integer> image2DataSource = DataSource.getImage2DataSource();
        ArrayList<String> newsAgencyDataSource = DataSource.getNewsAgencyDataSource();
        ArrayList<String> newsDescriptionDataSource = DataSource.getNewsDescriptionDataSource();


        linearLayoutManager2 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        newsAdapter = new NewsAdapter(newsId, image2DataSource, newsAgencyDataSource, newsDescriptionDataSource);
        rv2.setLayoutManager(linearLayoutManager2);
        rv2.setAdapter(newsAdapter);
    }
}
