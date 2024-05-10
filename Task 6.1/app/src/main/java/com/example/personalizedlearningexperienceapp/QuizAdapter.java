package com.example.personalizedlearningexperienceapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.personalizedlearningexperienceapp.Models.QuizResponse;


class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyHolder> {
    private List<QuizResponse.QuizResults> data;

    // Constructor to initialize adapter with data
    public QuizAdapter(List<QuizResponse.QuizResults> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout for each item view when View Holder is created
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        // Bind data to views
        QuizResponse.QuizResults item = data.get(position);
        holder.quizQuestion.setText(item.getQuestion());
        holder.option1.setText(item.getCorrect_answer());

        List<String> incorrectAnswers = item.getIncorrect_answers();

        int count = 2;
        for (String answer : incorrectAnswers) {
            if (count == 2){
                holder.option2.setText(answer);
                count--;
            }
            else if (count == 1){
                holder.option3.setText(answer);
                count--;
            }
        }


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    // ViewHolder class to hold item views
    class MyHolder extends RecyclerView.ViewHolder {
        TextView quizQuestion;
        RadioButton option1;
        RadioButton option2;
        RadioButton option3;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            quizQuestion = itemView.findViewById(R.id.questionTv);
            option1 = itemView.findViewById(R.id.option1);
            option2 = itemView.findViewById(R.id.option2);
            option3 = itemView.findViewById(R.id.option3);
        }
    }

}


