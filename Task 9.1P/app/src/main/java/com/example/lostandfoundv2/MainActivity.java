package com.example.lostandfoundv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView titleTv;
    Button createBtn;

    Button showBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        titleTv = findViewById(R.id.titleTv);
        createBtn = findViewById(R.id.createBtn);
        showBtn = findViewById(R.id.showBtn);

        // Set OnClickListener for create button, that leads to the activity where a new advert can  be created
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewAdvertActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for show button, this leads to the activity where all the adverts shown
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllAdvertsActivity.class);
                startActivity(intent);
            }
        });

    }
}