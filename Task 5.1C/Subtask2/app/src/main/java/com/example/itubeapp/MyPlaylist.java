package com.example.itubeapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MyPlaylist extends AppCompatActivity {

    private ListView listViewPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_playlist);

        // Initialize the ListView
        listViewPlaylist = findViewById(R.id.listViewPlaylist);

        // Retrieve the username passed from the previous activity
        String username = getIntent().getStringExtra("username");

        // Query the database to retrieve all URLs associated with the specific username
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<String> playlist = databaseHelper.getPlaylistForUser(username);

        // Create an ArrayAdapter to display the URLs in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playlist);

        // Set the adapter for the ListView
        listViewPlaylist.setAdapter(adapter);
    }
}
