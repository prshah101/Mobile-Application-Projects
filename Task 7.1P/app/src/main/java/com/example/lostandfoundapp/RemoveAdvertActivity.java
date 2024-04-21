package com.example.lostandfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

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

        // Initialize UI elements
        postTypeItemTv2 = findViewById(R.id.postTypeItemTv2);
        postItemTv2 = findViewById(R.id.postItemTv2);
        dateTv2 = findViewById(R.id.dateTv2);
        locationTv2 = findViewById(R.id.locationTv2);
        removeBtn = findViewById(R.id.removeBtn);
        backBtn3 = findViewById(R.id.backBtn3);

        //Set the Text View elements with the values from the previous activity, which is the AllAdvertActivity
        postTypeItemTv2.setText(lost);
        postItemTv2.setText(name);

        //Create a new instance of DatabaseHelper
        DatabaseHelper databaseHelper = new DatabaseHelper(RemoveAdvertActivity.this);

        //Se the location Text View to the location of the advert retrieved from the database using the name value
        Advert selectedAdvert = databaseHelper.getAdvertByName(postItemTv2.getText().toString());
        locationTv2.setText(selectedAdvert.getLocation());

        //Save the date value of that advert retrieved from the database
        Date selectedAdvertDate = selectedAdvert.getDate();
        //Get the current date
        Date currentDate = Calendar.getInstance().getTime();

        // Calculate the difference in milliseconds
        long differenceMillis = currentDate.getTime() - selectedAdvertDate.getTime();
        // Convert milliseconds to days
        long differenceDays = differenceMillis / (1000 * 60 * 60 * 24);
        //Set the date Text View to display the amount of days ago (in terms of the advert date and the current date) that the post was made
        dateTv2.setText(String.valueOf(differenceDays) + " days ago");

        // Set OnClickListener for remove button
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Remove the advert using the name
                boolean removed = databaseHelper.removeAdvertByName(postItemTv2.getText().toString());
                if (removed) {
                    Toast.makeText(getApplicationContext(), "Advert removed successfully", Toast.LENGTH_SHORT).show();
                    //Go back to previous page.
                    Intent intent = new Intent(RemoveAdvertActivity.this, AllAdvertsActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to remove advert", Toast.LENGTH_SHORT).show();

                }
            }
        });

        // Set OnClickListener for back button, this will take the user back to the All Adverts Activity.
        backBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RemoveAdvertActivity.this, AllAdvertsActivity.class);
                startActivity(intent);
            }
        });
    }
}
