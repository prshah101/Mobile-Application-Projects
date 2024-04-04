package com.example.task41;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task41.R;
import com.example.task41.ToDoItem;

import java.util.List;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.ViewHolder> {

    private List<ToDoItem> taskList;
    private final onItemClickListener listener;

    public VerticalAdapter(List<ToDoItem> taskList, onItemClickListener listener) {
        this.taskList = taskList;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView dueDateTextView;

        private TextView descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.taskTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.taskDescriptionTextView);
            dueDateTextView = itemView.findViewById(R.id.taskDueDateTextView);
        }

        public void bind(ToDoItem task, final onItemClickListener listener) {
            titleTextView.setText(task.getTitle());
            dueDateTextView.setText(task.getDueDate());
            descriptionTextView.setText(task.getDescription());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(task);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ToDoItem task = taskList.get(position);
        holder.bind(task, listener);
    }

    @Override
    public int getItemCount() {
        if (this.taskList == null) {
            return 0;
        }
        return this.taskList.size();
    }
}

