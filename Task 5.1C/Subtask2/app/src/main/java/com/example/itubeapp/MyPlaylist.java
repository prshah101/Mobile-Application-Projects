package com.example.itubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MyPlaylist extends AppCompatActivity {

    private ListView listViewPlaylist;

    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_playlist);

        // Initialize the elements
        listViewPlaylist = findViewById(R.id.listViewPlaylist);
        backBtn = findViewById(R.id.backBtn);

        // Retrieve the username passed from the previous activity called Home
        String username = getIntent().getStringExtra("username");

        // Query the database to retrieve all URLs associated with the specific username
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<String> playlist = databaseHelper.getPlaylistForUser(username);

        // Create a custom adapter and set it to the ListView
        PlaylistAdapter adapter = new PlaylistAdapter(this, playlist);
        listViewPlaylist.setAdapter(adapter);

        //Set OnClickListener for the back button to navigate back to Home
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // navigate back to home
                Intent intent = new Intent(MyPlaylist.this, Home.class);
                startActivity(intent);
            }
        });
    }

}
