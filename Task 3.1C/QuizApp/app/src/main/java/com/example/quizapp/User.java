package com.example.quizapp;

public class User {

    private String name;

    private static User instance;

    public static User getInstance() {
        if (instance == null) {
            instance = new User("UserName");
        }

        return instance;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}