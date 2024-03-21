package com.example.quizapp;

public class User { // This class represents a User

    // Private variable to store the user's name
    private String name;

    // Static variable to contain the instance of the User class
    private static User instance;

    // Get the single instance of the User class
    public static User getInstance() {
        // If instance is null, make the default name "UserName"
        if (instance == null) {
            instance = new User("UserName");
        }

        return instance;
    }

    // Private constructor to prevent instantiation from outside the User class
    public User(String name) {
        this.name = name;
    }

    // Method to get the name of the user
    public String getName() {
        return name;
    }

    // Method to set the name of the user
    public void setName(String name) {
        this.name = name;
    }
}