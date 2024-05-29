package com.example.personalizedlearningexperienceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.personalizedlearningexperienceapp.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table and column names for UserDetails
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_PHONE_NUMBER = "PHONE_NUMBER";

    // Table and column names for Playlist
    public static final String INTERESTS_TABLE = "INTERESTS_TABLE";
    public static final String COLUMN_INTERESTS_USERNAME = "USERNAME";
    public static final String COLUMN_INTEREST = "INTEREST";

    // Table and column names for Scores
    public static final String SCORES_TABLE = "SCORES_TABLE";
    public static final String COLUMN_SCORES_USERNAME = "USERNAME";
    public static final String COLUMN_TOTAL_SCORE = "TOTAL_SCORE";
    public static final String COLUMN_CORRECT_SCORE = "CORRECT_SCORE";
    public static final String COLUMN_WRONG_SCORE = "WRONG_SCORE";

    // Constructor to define database
    public DatabaseHelper(Context context) {
        super(context, "learningapp.db", null, 1);
    }

    // Create these tables in the SQLite database, if not already set up from previous app compilation
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create UserDetails table
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" +
                COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PHONE_NUMBER + " INTEGER)"; // Changed the column type to INTEGER
        db.execSQL(createTableStatement);

        // Create Playlist table
        String createPlaylistTableStatement = "CREATE TABLE " + INTERESTS_TABLE + " (" +
                COLUMN_INTERESTS_USERNAME + " TEXT, " +
                COLUMN_INTEREST + " TEXT, " +
                " FOREIGN KEY (" + COLUMN_INTERESTS_USERNAME + ") REFERENCES " + USER_TABLE + "(" + COLUMN_USERNAME + "))";
        db.execSQL(createPlaylistTableStatement);

        // Create Scores table
        String createScoresTableStatement = "CREATE TABLE " + SCORES_TABLE + " (" +
                COLUMN_SCORES_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_TOTAL_SCORE + " INTEGER, " +
                COLUMN_CORRECT_SCORE + " INTEGER, " +
                COLUMN_WRONG_SCORE + " INTEGER, " +
                " FOREIGN KEY (" + COLUMN_SCORES_USERNAME + ") REFERENCES " + USER_TABLE + "(" + COLUMN_USERNAME + "))";
        db.execSQL(createScoresTableStatement);
    }

    // Method called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement if needed
    }

    // Method to add a new UserDetails to the database
    public boolean addOneUser(UserDetails userDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME, userDetails.getUsername());
        cv.put(COLUMN_PASSWORD, userDetails.getPassword());
        cv.put(COLUMN_EMAIL, userDetails.getEmail());
        cv.put(COLUMN_PHONE_NUMBER, userDetails.getPhoneNumber()); // Using getPhoneNumber()

        long insert = db.insert(USER_TABLE, null, cv);
        return insert != -1;
    }

    // Method to retrieve a UserDetails from the database by username
    public UserDetails getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(queryString, new String[]{username});

        if (cursor.moveToFirst()) {
            String password = cursor.getString(1);
            String email = cursor.getString(2);
            int phoneNumber = cursor.getInt(3); // Changed to getInt()
            return new UserDetails(username, password, email, phoneNumber); // Changed to int
        } else {
            return null;
        }
    }

    // Method to retrieve a UserDetails from the database by username and password
    public UserDetails getUserByUsernameAndPassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(queryString, new String[]{username, password});

        if (cursor.moveToFirst()) {
            String email = cursor.getString(2);
            int phoneNumber = cursor.getInt(3); // Changed to getInt()
            return new UserDetails(username, password, email, phoneNumber); // Changed to int
        } else {
            return null;
        }
    }

    // Method to delete a UserDetails from the database
    public boolean deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(USER_TABLE, COLUMN_USERNAME + " = ?", new String[]{username});
        return rowsAffected > 0;
    }


    // Method to add a new interest entry to the database
    public boolean addInterest(String username, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_INTERESTS_USERNAME, username);
        cv.put(COLUMN_INTEREST, url);

        long insert = db.insert(INTERESTS_TABLE, null, cv);
        return insert != -1;
    }


    // Method to retrieve all interest entries for a given username

    public List<String> getPlaylistForUser(String username) {
        List<String> playlist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + INTERESTS_TABLE + " WHERE " + COLUMN_INTERESTS_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(queryString, new String[]{username});

        if (cursor.moveToFirst()) {
            do {
                String url = cursor.getString(1);
                playlist.add(url);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return playlist;
    }

    // Method to add or update scores for a user
    public boolean addOrUpdateScores(String username, int totalScore, int correctScore, int wrongScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SCORES_USERNAME, username);
        cv.put(COLUMN_TOTAL_SCORE, totalScore);
        cv.put(COLUMN_CORRECT_SCORE, correctScore);
        cv.put(COLUMN_WRONG_SCORE, wrongScore);

        // Try to update existing scores
        int rowsAffected = db.update(SCORES_TABLE, cv, COLUMN_SCORES_USERNAME + " = ?", new String[]{username});

        if (rowsAffected == 0) {
            // If no rows were updated, insert new scores
            long insert = db.insert(SCORES_TABLE, null, cv);
            return insert != -1;
        } else {
            return true;
        }
    }

    // Method to retrieve scores for a given username
    public Scores getScoresByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + SCORES_TABLE + " WHERE " + COLUMN_SCORES_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(queryString, new String[]{username});

        if (cursor.moveToFirst()) {
            int totalScore = cursor.getInt(1);
            int correctScore = cursor.getInt(2);
            int wrongScore = cursor.getInt(3);
            return new Scores(username, totalScore, correctScore, wrongScore);
        } else {
            return new Scores("Not Found", 0, 0, 0);
        }
    }
}

