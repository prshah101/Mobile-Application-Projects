package com.example.personalizedlearningexperienceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table and column names for UserDetails
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_PHONE_NUMBER = "PHONE_NUMBER";

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
                COLUMN_PHONE_NUMBER + " TEXT)";
        db.execSQL(createTableStatement);
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
        cv.put(COLUMN_PHONE_NUMBER, userDetails.getPhoneNumber());

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
            String phoneNumber = cursor.getString(3);
            return new UserDetails(username, password, email, phoneNumber);
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
            String phoneNumber = cursor.getString(3);
            return new UserDetails(username, password, email, phoneNumber);
        } else {
            return null;
        }
    }

    // Method to delete a UserDetails from the database
    public boolean deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected  = db.delete(USER_TABLE, COLUMN_USERNAME + " = ?", new String[]{username});
        return rowsAffected  > 0;
    }
}
