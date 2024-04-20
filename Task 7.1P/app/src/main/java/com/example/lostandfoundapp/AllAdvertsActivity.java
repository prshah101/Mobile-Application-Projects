package com.example.lostandfoundapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AllAdvertsActivity extends AppCompatActivity {
    RecyclerView allItemsRv;
    LinearLayoutManager linearLayoutManager;
    AdvertsAdapter advertsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.all_adverts);

        allItemsRv = findViewById(R.id.allItemsRv);

        // Initialize the LinearLayoutManager
        linearLayoutManager = new LinearLayoutManager(this);
        allItemsRv.setLayoutManager(linearLayoutManager);

        // Retrieve all adverts from the database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Advert> adverts = databaseHelper.getAllAdverts();

        // Create and set the AdvertsAdapter
        advertsAdapter = new AdvertsAdapter(adverts, this);
        allItemsRv.setAdapter(advertsAdapter);
    }
}
