package com.example.personalizedlearningexperienceapp;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.personalizedlearningexperienceapp.Models.QuizResponse;


class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.MyHolder> {
    public ArrayList<QuizResponse.QuizResults> data;

    // Constructor to initialize adapter with data
    public TopicsAdapter(List<QuizResponse.QuizResults> data) {
        this.data = (ArrayList<QuizResponse.QuizResults>) data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout for each item view when View Holder is created
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        // Bind data to views
        QuizResponse.QuizResults item = data.get(position);
        holder.generatedTaskTitle.setText("Generated Task " + (position+1));
        holder.generatedTaskDesc.setText("About " + item.getCategory());

        // Set click listeners for topic layout
        holder.chooseTopicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DoQuiz.class);
                intent.putExtra("selectedTopicData", data);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    // ViewHolder class to hold item views
    class MyHolder extends RecyclerView.ViewHolder {
        TextView generatedTaskTitle;
        TextView generatedTaskDesc;
        LinearLayout topic;
        Button chooseTopicBtn;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            generatedTaskTitle = itemView.findViewById(R.id.generatedTaskTitle);
            generatedTaskDesc = itemView.findViewById(R.id.generatedTaskDesc);
            chooseTopicBtn = itemView.findViewById(R.id.chooseTopicBtn);
        }
    }

}


