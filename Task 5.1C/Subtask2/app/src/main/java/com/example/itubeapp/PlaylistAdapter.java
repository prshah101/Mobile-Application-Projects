package com.example.itubeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<String> {

    public PlaylistAdapter(Context context, List<String> playlist) {
        super(context, 0, playlist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String url = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.playlist_item, parent, false);
        }

        // Lookup view for data population
        TextView textView = convertView.findViewById(R.id.playlistItemText);

        // Populate the data into the template view (playlist_item) using the data object
        textView.setText(url);

        // Return the completed view to render on screen
        return convertView;
    }
}
