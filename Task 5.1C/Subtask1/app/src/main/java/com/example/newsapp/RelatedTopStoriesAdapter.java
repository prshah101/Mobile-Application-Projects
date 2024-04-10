package com.example.newsapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


class RelatedTopStoriesAdapter extends RecyclerView.Adapter<RelatedTopStoriesAdapter.MyHolder> {
    ArrayList<Integer> topStoriesIds;
    ArrayList<Integer> imageResourceIds;
    ArrayList<String> titles;
    ArrayList<String> description;
    public RelatedTopStoriesAdapter(ArrayList<Integer> topStoriesIds, ArrayList<Integer> imageResourceIds, ArrayList<String> titles, ArrayList<String> description) {
        this.topStoriesIds = topStoriesIds;
        this.titles = titles;
        this.imageResourceIds = imageResourceIds;
        this.description = description;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv3_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.titleText.setText(titles.get(position));
        holder.descriptionText.setText(description.get(position));
        holder.newStoryImage.setImageResource(imageResourceIds.get(position));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedId = topStoriesIds.get(position); // Get ID from newsIds array
                Intent intent = new Intent(v.getContext(), NewsDetails.class);
                intent.putExtra("selectedId", clickedId);
                intent.putExtra("fromTopStories", true);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Assume both ArrayLists have the same size
        return titles.size();
    }

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