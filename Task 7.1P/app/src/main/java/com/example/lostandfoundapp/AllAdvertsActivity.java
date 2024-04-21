package com.example.lostandfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AllAdvertsActivity extends AppCompatActivity {
    RecyclerView allItemsRv;
    LinearLayoutManager linearLayoutManager;
    AdvertsAdapter advertsAdapter;
    Button backBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.all_adverts);

        // Initialize UI elements
        allItemsRv = findViewById(R.id.allItemsRv);
        backBtn2= findViewById(R.id.backBtn2);

        // Initialize the LinearLayoutManager
        linearLayoutManager = new LinearLayoutManager(this);
        allItemsRv.setLayoutManager(linearLayoutManager);

        // Retrieve all adverts from the database, using an instance of databaseHelper
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Advert> adverts = databaseHelper.getAllAdverts();

        // Create and set the AdvertsAdapter
        advertsAdapter = new AdvertsAdapter(adverts, this);
        allItemsRv.setAdapter(advertsAdapter);

        // Set OnClickListener for back button, which enables user to go back to the Main Activity
        backBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllAdvertsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
