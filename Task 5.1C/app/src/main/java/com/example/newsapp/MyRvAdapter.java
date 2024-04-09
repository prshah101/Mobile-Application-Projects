package com.example.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.TextView;


class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {
    ArrayList<String> titles;
    ArrayList<Integer> imageResourceIds;
    public MyRvAdapter(ArrayList<String> titles, ArrayList<Integer> imageResourceIds) {
        this.titles = titles;
        this.imageResourceIds = imageResourceIds;
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
    }

    @Override
    public int getItemCount() {
        // Assume both ArrayLists have the same size
        return titles.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView tvImage;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvImage = itemView.findViewById(R.id.tvImage);
        }
    }

}