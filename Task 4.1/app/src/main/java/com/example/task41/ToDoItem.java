package com.example.task41;

public class ToDoItem {
    private String title;
    private String description;

    public ToDoItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public ToDoItem() {
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
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
}
