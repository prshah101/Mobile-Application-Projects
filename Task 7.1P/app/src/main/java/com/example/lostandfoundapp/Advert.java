package com.example.lostandfoundapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//This class defines the variables of an Advert Object
public class Advert {
    private String name;
    private int phone;
    private String description;
    private Date date;
    private String location;
    private boolean isLost;

    // Constructor
    public Advert(String name, int phone, String description, String date, String location, boolean isLost) {
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = parseDate(date);
        this.location = location;
        this.isLost = isLost;
    }

    // Method to parse date string into Date object
    private Date parseDate(String dateString) {
        try {
            //SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"); // Specify the date format
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Specify the date format
            return sdf.parse(dateString); // Parse the date string
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Return null in case of parse error
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }
}
