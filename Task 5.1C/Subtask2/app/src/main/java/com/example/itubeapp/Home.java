package com.example.itubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    private TextView homeTitle;
    private EditText urlText;
    private Button playBtn;
    private Button addPlayBtn;
    private Button myPlaylistBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        // Initialize views
        homeTitle = findViewById(R.id.homeTitle);
        urlText = findViewById(R.id.urlText);
        playBtn = findViewById(R.id.playBtn);
        addPlayBtn = findViewById(R.id.addPlayBtn);
        myPlaylistBtn = findViewById(R.id.myPlaylistBtn);

        String username = getIntent().getStringExtra("username");

        // Set click listeners
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle play button click
                String url = urlText.getText().toString();
                // logic to play the video
            }
        });

        addPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve username from intent extra
                String username = getIntent().getStringExtra("username");

                // Get the video URL from the EditText
                String videoUrl = urlText.getText().toString();

                // Insert the username and URL into the PLAY_LIST table
                DatabaseHelper databaseHelper = new DatabaseHelper(Home.this);
                boolean success = databaseHelper.addPlaylistEntry(username, videoUrl);

                // Check if the insertion was successful and display a toast message accordingly
                if (success) {
                    // Video added successfully
                    Toast.makeText(Home.this, "Video added to playlist", Toast.LENGTH_SHORT).show();
                } else {
                    // Failed to add the video
                    Toast.makeText(Home.this, "Failed to add video to playlist", Toast.LENGTH_SHORT).show();
                }
            }
        });


        myPlaylistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // navigate to the playlist activity
                Intent intent = new Intent(Home.this, MyPlaylist.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}
