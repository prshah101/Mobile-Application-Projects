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

        // Get username from the intent extra passed through MainActivity
        String username = getIntent().getStringExtra("username");

        // Set click listener for play video from url button
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle play button click
                String url = urlText.getText().toString();

                // Check if the URL is valid
                if (PlayVideo.getYoutubeId(url) == null){
                    // Show error message for invalid URL, since getYoutubeId() returned null
                    Toast.makeText(Home.this, "Invalid URL, Can't play video", Toast.LENGTH_SHORT).show();
                }
                else{
                    // Navigate to the PlayVideo activity
                    Intent intent = new Intent(Home.this, PlayVideo.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                }
            }
        });

        // Set click listener for add url to playlist button
        addPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the video URL from the EditText
                String url = urlText.getText().toString();
                // Check if the URL is valid
                if (PlayVideo.getYoutubeId(url) == null){
                    // Show error message for invalid URL, since getYoutubeId() returned null
                    Toast.makeText(Home.this, "Invalid URL, Can't add to playlist", Toast.LENGTH_SHORT).show();
                }
                else{
                    // Insert the username and URL into the PLAY_LIST table
                    DatabaseHelper databaseHelper = new DatabaseHelper(Home.this);
                    boolean success = databaseHelper.addPlaylistEntry(username, url);

                    // Check if the insertion was successful and display a toast message accordingly
                    if (success) {
                        // Video added successfully
                        Toast.makeText(Home.this, "Video added to playlist", Toast.LENGTH_SHORT).show();
                    } else {
                        // Failed to add the video
                        Toast.makeText(Home.this, "Failed to add video to playlist", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        // Set click listener for list my playlist urls button
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
