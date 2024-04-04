package com.example.task41;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

//Create the Adapter class for populating RecyclerView with vertical list of tasks
public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.ViewHolder> {

    // Declare a variable to hold the list of tasks
    private final List<ToDoItem> taskList;

    // Click listener for item clicks
    private final onItemClickListener listener;

    // Constructor to initialize the adapter it's variables
    public VerticalAdapter(List<ToDoItem> taskList, onItemClickListener listener) {
        this.taskList = taskList;
        this.listener = listener;
    }

    // ViewHolder class to hold and manage views for each task item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Declare the necessary variables
        private final TextView titleTextView; // Will display task title
        private final TextView dueDateTextView; // Will display task due date

        private final TextView descriptionTextView; // Will display task description

        // Constructor to initialize views from task_item.xml
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.taskTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.taskDescriptionTextView);
            dueDateTextView = itemView.findViewById(R.id.taskDueDateTextView);
        }

        // Method to bind task data to views and set click the item click listener
        public void bind(ToDoItem task, final onItemClickListener listener) {
            titleTextView.setText(task.getTitle());
            dueDateTextView.setText(task.getDueDate());
            descriptionTextView.setText(task.getDescription());

            // Set click listener for item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(task); // Notify listener of item click (Enables Adapter to listen for clicks on items)
                }
            });
        }
    }

    // Method to create a ViewHolder instance
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    // Method to bind task data to ViewHolder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ToDoItem task = taskList.get(position);
        holder.bind(task, listener);
    }

    // Method to return number of tasks in the list
    @Override
    public int getItemCount() {
        // If taskList is null and return size 0
        if (this.taskList == null) {
            return 0;
        }
        //Else return size value from taskList
        return this.taskList.size();
    }
}

