package com.example.task41;

// An Interface to handle item click events in RecyclerView
public interface onItemClickListener {
    // Method to be called when an item is clicked, with the clicked ToDoItem as a parameter
    void itemClick(ToDoItem task);
}
