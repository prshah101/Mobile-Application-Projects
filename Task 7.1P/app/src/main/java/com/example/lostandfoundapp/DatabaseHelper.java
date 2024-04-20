package com.example.lostandfoundapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lostandfoundapp.Advert;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table and column names for Adverts
    public static final String ADVERTS_TABLE = "ADVERTS_TABLE";
    public static final String COLUMN_ID = "ID"; // Added column for advert_id
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_PHONE = "PHONE";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_LOCATION = "LOCATION";
    public static final String COLUMN_IS_LOST = "IS_LOST";

    //Constructor to define database
    public DatabaseHelper(@Nullable Context context) {
        super(context, "lostandfound.db", null, 1);
    }

    //Create the Adverts table in the SQLite database
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Adverts table
        String createTableStatement = "CREATE TABLE " + ADVERTS_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // Added ID with AUTOINCREMENT
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " INTEGER, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_IS_LOST + " INTEGER)";
        db.execSQL(createTableStatement);
    }

    // Method called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement if needed
    }

    // Method to add a new advert to the database
    public boolean addOneAdvert(Advert advert) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, advert.getName());
        cv.put(COLUMN_PHONE, advert.getPhone());
        cv.put(COLUMN_DESCRIPTION, advert.getDescription());
        cv.put(COLUMN_DATE, advert.getDate().toString());
        cv.put(COLUMN_LOCATION, advert.getLocation());
        cv.put(COLUMN_IS_LOST, advert.isLost() ? 1 : 0);

        long insert = db.insert(ADVERTS_TABLE, null, cv);
        return insert != -1;
    }

    // Method to retrieve all adverts from the database
    public List<Advert> getAllAdverts() {
        List<Advert> adverts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + ADVERTS_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                Integer phone = cursor.getInt(2);
                String description = cursor.getString(3);
                String dateString = cursor.getString(4);
                String location = cursor.getString(5);
                boolean isLost = cursor.getInt(6) == 1;

                // Create Advert object and add to list
                Advert advert = new Advert(name, phone, description, dateString, location, isLost);
                adverts.add(advert);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return adverts;
    }

    public boolean removeAdvertByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Define the where clause
        String whereClause = COLUMN_NAME + "=?";
        // Define the arguments for the where clause
        String[] whereArgs = {name};
        // Execute the delete query
        int rowsDeleted = db.delete(ADVERTS_TABLE, whereClause, whereArgs);
        // Return true if at least one row was deleted, false otherwise
        return rowsDeleted > 0;
    }
}
