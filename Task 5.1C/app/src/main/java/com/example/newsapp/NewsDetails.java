package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
        Button backtoMainBtn = findViewById(R.id.backBtn);

        String selectedItemDueDate = getIntent().getStringExtra("selectedDescription");
        //Set the values retrieved from MainActivity
        selectedDescriptionText = findViewById(R.id.selectedDescriptionText);
        Log.i("Test", selectedItemDueDate);
        selectedDescriptionText.setText(selectedItemDueDate);


        backtoMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);

            }
        });

    }
}