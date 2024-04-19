package com.example.personalizedlearningexperienceapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private ArrayList<String> topics1;
    private ArrayList<String> topics2;

    private boolean isSelectMode = false;

    private boolean isSelectMode2 = false;

    private List<String> selectedIntrestsList = new ArrayList<>();

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

        // Set click listener for the card
        holder.topicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSelectMode){
                    if (selectedIntrestsList.size() == 10) {
                        // Display toast message if the list size is 10
                        Toast.makeText(holder.itemView.getContext(), "You can't select more than 10 interests.", Toast.LENGTH_SHORT).show();
                    }else{
                        holder.topicButton.setBackgroundResource(R.drawable.rectangle_button_background_outlined_selected);
                        selectedIntrestsList.add(topics1.get(position));
                        isSelectMode = true;
                    }
                } else{
                    holder.topicButton.setBackgroundResource(R.drawable.rectangle_button_background_outlined);
                    selectedIntrestsList.remove(topics1.get(position));
                    isSelectMode = false;
                }

//                // Print all the values in selectedIntrestsList
//                for (String interest : selectedIntrestsList) {
//                    Toast.makeText(holder.itemView.getContext(), interest, Toast.LENGTH_SHORT).show();
//                }
            }
        });

        holder.topicButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSelectMode2){
                    if (selectedIntrestsList.size() == 10) {
                        // Display toast message if the list size is 10
                        Toast.makeText(holder.itemView.getContext(), "You can't select more than 10 interests.", Toast.LENGTH_SHORT).show();
                    }else{
                        holder.topicButton2.setBackgroundResource(R.drawable.rectangle_button_background_outlined_selected);
                        selectedIntrestsList.add(topics2.get(position));
                        isSelectMode2 = true;
                    }
                } else{
                    holder.topicButton2.setBackgroundResource(R.drawable.rectangle_button_background_outlined);
                    selectedIntrestsList.remove(topics2.get(position));
                    isSelectMode2 = false;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return topics1.size();
    }


    public List<String> getSelectedIntrestsList() {
        return selectedIntrestsList;
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
