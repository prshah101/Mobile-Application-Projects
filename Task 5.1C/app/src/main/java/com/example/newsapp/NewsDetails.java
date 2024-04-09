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
import android.widget.ImageView;

public class NewsDetails extends AppCompatActivity {
    TextView selectedDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news_details);
        Button backtoMainBtn = findViewById(R.id.backBtn);

        //String selectedItemDueDate = getIntent().getStringExtra("selectedDescription");
//        //Set the values retrieved from MainActivity
//        selectedDescriptionText = findViewById(R.id.selectedDescriptionText);
//        Log.i("Test", selectedItemDueDate);
//        selectedDescriptionText.setText(selectedItemDueDate);
        TextView selectedDescriptionText = findViewById(R.id.selectedDescriptionText);
        TextView selectedTitleText = findViewById(R.id.selectedTitleText);
        ImageView selectedImageView = findViewById(R.id.newsImage);

        int  id = getIntent().getIntExtra("selectedId", 0);
//        String idString = String.valueOf(id);
//        selectedDescriptionText.setText(idString);
        boolean fromTopStories = getIntent().getBooleanExtra("fromTopStories", false);

        if (fromTopStories == true){
            //Set the values retrieved from MainActivity
            selectedDescriptionText.setText(DataSource.getDescriptionDataSource().get(id - 1)); // Adjusting index by 1 since IDs start from 1
            // Set the news title
            selectedTitleText.setText(DataSource.getTitleDataSource().get(id - 1)); // Adjusting index by 1
//            // Set the news image
            selectedImageView.setImageResource(DataSource.getImageDataSource().get(id - 1));
        }else{
            //Set the values retrieved from MainActivity
            selectedDescriptionText.setText(DataSource.getNewsDescriptionDataSource().get(id - 1)); // Adjusting index by 1 since IDs start from 1
            // Set the news title
            selectedTitleText.setText(DataSource.getNewsAgencyDataSource().get(id - 1)); // Adjusting index by 1
            // Set the news image
            selectedImageView.setImageResource(DataSource.getImage2DataSource().get(id - 1));
        }

        backtoMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);

            }
        });

    }
}