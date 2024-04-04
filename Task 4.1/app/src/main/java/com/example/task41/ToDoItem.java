package com.example.task41;


// Class define a single ToDoItem (or Task)
public class ToDoItem {

    //Declare the private variables of ToDoItem
    private String title;
    private String description;
    private String dueDate;

    // Constructor to initialize a ToDoItem's variables
    public ToDoItem(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate; // Initialize the date variable
    }

    // Default constructor
    public ToDoItem() {
    }

    // Override toString method to provide a string representation of ToDoItem
    @Override
    public String toString() {
        return "ToDoItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' + // Include the date variable in the toString method
                '}';
    }

    // Getter method for title
    public String getTitle() {
        return title;
    }

    // Setter method for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter method for description
    public String getDescription() {
        return description;
    }

    // Setter method for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter method for due date
    public String getDueDate() {
        return dueDate;
    }

    // Setter method for due date
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
