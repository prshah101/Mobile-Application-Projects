package com.example.personalizedlearningexperienceapp;

import java.util.ArrayList;

public class DataSource {

    ///For Top Stories
    // Method to get titles of top stories
    public static ArrayList<String> geTopics() {
        ArrayList<String> titleDataSource = new ArrayList<>();
        titleDataSource.add("Algorithms");
        titleDataSource.add("Data Structures");
        titleDataSource.add("Web Development");
        titleDataSource.add("Testing");
        return titleDataSource;
    }


}
