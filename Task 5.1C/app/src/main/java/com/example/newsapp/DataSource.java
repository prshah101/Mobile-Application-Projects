package com.example.newsapp;

import java.util.ArrayList;

public class DataSource {

    ///For Top Stories
    public static ArrayList<String> getTitleDataSource() {
        ArrayList<String> titleDataSource = new ArrayList<>();
        titleDataSource.add("WBS Stock Falling");
        titleDataSource.add("Senetor Announces Plans");
        titleDataSource.add("New Law Passed");
        titleDataSource.add("Team at Championship");
        return titleDataSource;
    }

    public static ArrayList<Integer> getImageDataSource() {
        ArrayList<Integer> imageDataSource = new ArrayList<>();
        imageDataSource.add(R.drawable.top1);
        imageDataSource.add(R.drawable.top2);
        imageDataSource.add(R.drawable.top3);
        imageDataSource.add(R.drawable.top4);
        return imageDataSource;
    }

    ///For News

    public static ArrayList<Integer> getImage2DataSource() {
        ArrayList<Integer> image2DataSource = new ArrayList<>();
        image2DataSource.add(R.drawable.news1);
        image2DataSource.add(R.drawable.news2);
        image2DataSource.add(R.drawable.news3);
        image2DataSource.add(R.drawable.news4);
        return image2DataSource;
    }

    public static ArrayList<String> getNewsAgencyDataSource() {
        ArrayList<String> newsAgencyDataSource = new ArrayList<>();
        newsAgencyDataSource.add("9 News");
        newsAgencyDataSource.add("7 News");
        newsAgencyDataSource.add("ABC News");
        newsAgencyDataSource.add("The Age");
        return newsAgencyDataSource;
    }

    public static ArrayList<String> getNewsDescriptionDataSource() {
        ArrayList<String> newsDescriptionDataSource = new ArrayList<>();
        newsDescriptionDataSource.add("Dog returns home");
        newsDescriptionDataSource.add("Firefighters Resigning");
        newsDescriptionDataSource.add("Visa restrictions changed");
        newsDescriptionDataSource.add("Best Investments in 2024");
        return newsDescriptionDataSource;
    }



}
