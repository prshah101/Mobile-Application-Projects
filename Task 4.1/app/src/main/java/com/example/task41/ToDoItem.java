package com.example.task41;

public class ToDoItem {
    private String title;
    private String description;
    private String dueDate; // Add a date variable

    public ToDoItem(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate; // Initialize the date variable
    }

    public ToDoItem() {
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' + // Include the date variable in the toString method
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
