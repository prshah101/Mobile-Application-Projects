package com.example.newsapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewsDetails extends AppCompatActivity {
    TextView selectedDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news_details);


        String selectedItemDueDate = getIntent().getStringExtra("selectedDescription");
        //Set the values retrieved from MainActivity
        selectedDescriptionText = findViewById(R.id.selectedDescriptionText);
        Log.i("Test", selectedItemDueDate);
        selectedDescriptionText.setText(selectedItemDueDate);

    }
}