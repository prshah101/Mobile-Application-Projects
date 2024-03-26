package com.example.task41;

public class Task {
    private String taskTitle;
    private String dueDate;
    private String description;

    public Task(String taskTitle, String dueDate, String description) {
        this.taskTitle = taskTitle;
        this.dueDate = dueDate;
        this.description = description;
    }

    // Getters and Setters
    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
