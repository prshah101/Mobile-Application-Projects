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

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private ArrayList<String> topics1;
    private ArrayList<String> topics2;

    public boolean row1 = true;

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


        if (row1 ==true){
            // Set the layout width of the buttons programmatically
            ViewGroup.LayoutParams params = holder.topicButton.getLayoutParams();
            params.width = 375; // Set your desired width here
            holder.topicButton.setLayoutParams(params);

            ViewGroup.LayoutParams params2 = holder.topicButton2.getLayoutParams();
            params2.width = 535; // Set your desired width here
            holder.topicButton2.setLayoutParams(params2);
            row1 = false;
        }
        else{
            // Set the layout width of the buttons programmatically
            ViewGroup.LayoutParams params = holder.topicButton.getLayoutParams();
            params.width = 535; // Set your desired width here
            holder.topicButton.setLayoutParams(params);

            ViewGroup.LayoutParams params2 = holder.topicButton2.getLayoutParams();
            params2.width = 375; // Set your desired width here
            holder.topicButton2.setLayoutParams(params2);
            row1 = true;
        }

        // Set click listener for the card
        holder.topicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clickedId = topics1.get(position); // Get ID from newsIds array
                Intent intent = new Intent(v.getContext(), MainActivity.class);
//                intent.putExtra("selectedId", clickedId);
//                intent.putExtra("fromTopStories", true);
                v.getContext().startActivity(intent);
            }
        });

        holder.topicButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clickedId = topics2.get(position); // Get ID from newsIds array
                Intent intent = new Intent(v.getContext(), MainActivity.class);
//                intent.putExtra("selectedId", clickedId);
//                intent.putExtra("fromTopStories", true);
                v.getContext().startActivity(intent);
            }
        });
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

