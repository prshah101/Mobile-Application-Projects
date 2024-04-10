package com.example.itubeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table and column names for LoginDetails
    public static final String LOGIN_TABLE = "LOGIN_TABLE";
    public static final String COLUMN_FULL_NAME = "FULL_NAME";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";

    // Table and column names for Playlist
    public static final String PLAYLIST_TABLE = "PLAYLIST_TABLE";
    public static final String COLUMN_PLAYLIST_USERNAME = "USERNAME";
    public static final String COLUMN_PLAYLIST_URL = "URL";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "itube.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create LoginDetails table
        String createTableStatement = "CREATE TABLE " + LOGIN_TABLE + " (" +
                COLUMN_FULL_NAME + " TEXT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableStatement);

        // Create Playlist table
        String createPlaylistTableStatement = "CREATE TABLE " + PLAYLIST_TABLE + " (" +
                COLUMN_PLAYLIST_USERNAME + " TEXT, " +
                COLUMN_PLAYLIST_URL + " TEXT, " +
                " FOREIGN KEY (" + COLUMN_PLAYLIST_USERNAME + ") REFERENCES " + LOGIN_TABLE + "(" + COLUMN_USERNAME + "))";
        db.execSQL(createPlaylistTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement if needed
    }

    // Method to add a new LoginDetails to the database
    public boolean addOneLogin(LoginDetails loginDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FULL_NAME, loginDetails.getFullName());
        cv.put(COLUMN_USERNAME, loginDetails.getUsername());
        cv.put(COLUMN_PASSWORD, loginDetails.getPassword());

        long insert = db.insert(LOGIN_TABLE, null, cv);
        return insert != -1;
    }

    // Method to retrieve a LoginDetails from the database by username
    public LoginDetails getLoginByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + LOGIN_TABLE + " WHERE " + COLUMN_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(queryString, new String[]{username});

        if (cursor.moveToFirst()) {
            String fullName = cursor.getString(0);
            String password = cursor.getString(2);
            return new LoginDetails(fullName, username, password);
        } else {
            return null;
        }
    }

    // Method to retrieve a LoginDetails from the database by username and password
    public LoginDetails getLoginByUsernameAndPassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + LOGIN_TABLE + " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(queryString, new String[]{username, password});

        if (cursor.moveToFirst()) {
            String fullName = cursor.getString(0);
            return new LoginDetails(fullName, username, password);
        } else {
            return null;
        }
    }


    // Method to delete a LoginDetails from the database
    public boolean deleteLogin(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected  = db.delete(LOGIN_TABLE, COLUMN_USERNAME + " = ?", new String[]{username});
        return rowsAffected  > 0;
    }


    ////////////////For Playlist//////////////
    // Method to add a new playlist entry to the database
    public boolean addPlaylistEntry(String username, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PLAYLIST_USERNAME, username);
        cv.put(COLUMN_PLAYLIST_URL, url);

        long insert = db.insert(PLAYLIST_TABLE, null, cv);
        return insert != -1;
    }

    // Method to retrieve all playlist entries for a given username
    public List<String> getPlaylistForUser(String username) {
        List<String> playlist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + PLAYLIST_TABLE + " WHERE " + COLUMN_PLAYLIST_USERNAME + " = ?";
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
}
