package com.example.personalizedlearningexperienceapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;


public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private ArrayList<String> topics1;
    private ArrayList<String> topics2;

    public TopicAdapter(ArrayList<String> topics1, ArrayList<String> topics2) {
        this.topics1 = topics1;
        this.topics2 = topics2;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_interest, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        String topic1 = topics1.get(position);
        String topic2 = topics2.get(position);
        holder.bind(topic1, topic2);
    }

    @Override
    public int getItemCount() {
        return topics1.size();
    }


    public class TopicViewHolder extends RecyclerView.ViewHolder {

        private Button topicButton;
        private Button topicButton2;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            topicButton = itemView.findViewById(R.id.interestBtn);
            topicButton2 = itemView.findViewById(R.id.interestBtn2);
        }

        public void bind(String topic1, String topic2) {
            topicButton.setText(topic1);
            topicButton2.setText(topic2);
        }
    }
}
