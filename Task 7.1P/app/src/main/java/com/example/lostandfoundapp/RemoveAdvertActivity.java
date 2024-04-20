package com.example.lostandfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RemoveAdvertActivity extends AppCompatActivity {

    TextView postTypeItemTv2;
    TextView postItemTv2;
    TextView dateTv2;
    TextView locationTv2;
    Button removeBtn;

    Button backBtn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.remove_advert);

        // Retrieve selected news ID and source information from MainActivity page
        String name = getIntent().getStringExtra("Name");
        String lost = getIntent().getStringExtra("Lost");

        postTypeItemTv2 = findViewById(R.id.postTypeItemTv2);
        postItemTv2 = findViewById(R.id.postItemTv2);
        dateTv2 = findViewById(R.id.dateTv2);
        locationTv2 = findViewById(R.id.locationTv2);
        removeBtn = findViewById(R.id.removeBtn);
        backBtn3 = findViewById(R.id.backBtn3);

        postTypeItemTv2.setText(lost);
        postTypeItemTv2.setText(name);

        // Set OnClickListener for remove button
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(RemoveAdvertActivity.this, AllAdvertsActivity.class);
//                startActivity(intent);
            }
        });

        // Set OnClickListener for back button
        backBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RemoveAdvertActivity.this, AllAdvertsActivity.class);
                startActivity(intent);
            }
        });
    }
}
