package com.example.newsapp;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.TextView;


class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder> {
    ArrayList<Integer> imageResourceIds;
    ArrayList<String> newsAgency;
    ArrayList<String> newsDescription;
    public NewsAdapter(ArrayList<Integer> imageResourceIds, ArrayList<String> newsAgency, ArrayList<String> newsDescription) {
        this.imageResourceIds = imageResourceIds;
        this.newsAgency = newsAgency;
        this.newsDescription = newsDescription;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv2_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.newsAgency.setText(newsAgency.get(position));
        holder.newsDescription.setText(newsDescription.get(position));
        holder.newsImage.setImageResource(imageResourceIds.get(position));

        holder.newsAgency2.setText(newsAgency.get(position));
        holder.newsDescription2.setText(newsDescription.get(position));
        holder.newsImage2.setImageResource(imageResourceIds.get(position));

        holder.newsDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewsDetails.class);
                intent.putExtra("selectedDescription", newsDescription.get(position));
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        // Assume all ArrayLists have the same size
        return newsAgency.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView newsAgency;
        TextView newsDescription;

        ImageView newsImage;

        TextView newsAgency2;
        TextView newsDescription2;

        ImageView newsImage2;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.rv2tvImage1);
            newsAgency = itemView.findViewById(R.id.rv2tvTitle1);
            newsDescription = itemView.findViewById(R.id.rv2tvDescription1);

            newsImage2 = itemView.findViewById(R.id.rv2tvImage2);
            newsAgency2 = itemView.findViewById(R.id.rv2tvTitle2);
            newsDescription2 = itemView.findViewById(R.id.rv2tvDescription2);
        }
    }

}


///////////