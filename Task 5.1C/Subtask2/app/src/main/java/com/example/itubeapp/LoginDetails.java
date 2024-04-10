package com.example.itubeapp;
public class LoginDetails {
    private String fullName;
    private String username;
    private String password;

    // Constructor
    public LoginDetails(String fullName, String username, String password) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
