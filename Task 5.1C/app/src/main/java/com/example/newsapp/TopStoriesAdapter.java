package com.example.newsapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.TextView;


class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.MyHolder> {
    ArrayList<Integer> topStoriesIds;
    ArrayList<Integer> imageResourceIds;
    ArrayList<String> titles;
    ArrayList<String> description;
    public TopStoriesAdapter(ArrayList<Integer> topStoriesIds, ArrayList<Integer> imageResourceIds, ArrayList<String> titles, ArrayList<String> description) {
        this.topStoriesIds = topStoriesIds;
        this.titles = titles;
        this.imageResourceIds = imageResourceIds;
        this.description = description;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv1_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.tvTitle.setText(titles.get(position));
        holder.tvImage.setImageResource(imageResourceIds.get(position));

        holder.card.setOnClickListener(new View.OnClickListener() {
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

        TextView tvTitle;
        ImageView tvImage;

        CardView card;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.rv2tvTitle1);
            tvImage = itemView.findViewById(R.id.rv2tvImage1);
            card = itemView.findViewById(R.id.card);
        }
    }

}