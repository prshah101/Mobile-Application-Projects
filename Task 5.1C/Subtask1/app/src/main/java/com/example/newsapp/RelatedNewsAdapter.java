package com.example.newsapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.MyHolder> {
    ArrayList<Integer> newsIds;
    ArrayList<Integer> imageResourceIds;
    ArrayList<String> newsAgency;
    ArrayList<String> newsDescription;

    // Constructor to initialize adapter with data
    public RelatedNewsAdapter(ArrayList<Integer> newsIds, ArrayList<Integer> imageResourceIds, ArrayList<String> newsAgency, ArrayList<String> newsDescription) {
        this.newsIds = newsIds;
        this.imageResourceIds = imageResourceIds;
        this.newsAgency = newsAgency;
        this.newsDescription = newsDescription;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout for each item view when View Holder is created
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv3_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        // Bind data to views
        holder.titleText.setText(newsAgency.get(position));
        holder.descriptionText.setText(newsDescription.get(position));
        holder.newStoryImage.setImageResource(imageResourceIds.get(position));


        // Set click listener for layout
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedId = newsIds.get(position); // Get ID from newsIds array
                Intent intent = new Intent(v.getContext(), NewsDetails.class);
                intent.putExtra("selectedId", clickedId);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        // Assume all ArrayLists have the same size
        return newsAgency.size();
    }

    // ViewHolder class to hold item views
    class MyHolder extends RecyclerView.ViewHolder {
        ImageView newStoryImage;

        TextView titleText;
        TextView descriptionText;

        LinearLayout layout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            newStoryImage = itemView.findViewById(R.id.newStoryImage);
            titleText = itemView.findViewById(R.id.titleText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
            layout = itemView.findViewById(R.id.layout);
        }
    }

}


