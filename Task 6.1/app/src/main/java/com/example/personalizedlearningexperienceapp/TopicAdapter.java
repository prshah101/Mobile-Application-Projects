package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private ArrayList<String> topics;

    public TopicAdapter(ArrayList<String> topics) {
        this.topics = topics;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_interest, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        String topic = topics.get(position);
        holder.bind(topic);


        // Set click listener for the card
        holder.topicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clickedId = topics.get(position); // Get ID from newsIds array
                Intent intent = new Intent(v.getContext(), MainActivity.class);
//                intent.putExtra("selectedId", clickedId);
//                intent.putExtra("fromTopStories", true);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {

        private Button topicButton;
        private Button topicButton2;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            topicButton = itemView.findViewById(R.id.interestBtn);
            topicButton2 = itemView.findViewById(R.id.interestBtn2);
        }

        public void bind(String topic) {
            topicButton.setText(topic);
            topicButton2.setText(topic);
        }
    }
}

