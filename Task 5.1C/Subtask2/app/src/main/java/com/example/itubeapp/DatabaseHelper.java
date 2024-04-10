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

    public DatabaseHelper(@Nullable Context context) {
        super(context, "itube.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + LOGIN_TABLE + " (" +
                COLUMN_FULL_NAME + " TEXT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement if needed
    }

    // Method to add a new LoginDetails to the database
    public boolean addLogin(LoginDetails loginDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FULL_NAME, loginDetails.getFullName());
        cv.put(COLUMN_USERNAME, loginDetails.getUsername());
        cv.put(COLUMN_PASSWORD, loginDetails.getPassword());

        long insert = db.insert(LOGIN_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
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

//    // Method to update an existing LoginDetails in the database
//    public boolean updateLogin(LoginDetails loginDetails) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(COLUMN_FULL_NAME, loginDetails.getFullName());
//        cv.put(COLUMN_PASSWORD, loginDetails.getPassword());
//
//        int update = db.update(LOGIN_TABLE, cv, COLUMN_USERNAME + " = ?", new String[]{loginDetails.getUsername()});
//                // Check if any rows were updated
//        if (updatedRows == 0) {
//            return false; // No rows were updated, so return false
//        } else {
//            return true; // At least one row was updated, so return true
//        }
//    }

    // Method to delete a LoginDetails from the database
    public boolean deleteLogin(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected  = db.delete(LOGIN_TABLE, COLUMN_USERNAME + " = ?", new String[]{username});
        return rowsAffected  > 0;
    }
}
