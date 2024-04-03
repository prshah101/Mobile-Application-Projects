package com.example.task41;

public class ToDoItem {
    private static String title;
    private static String description;

    public ToDoItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
