package com.example.lostandfoundv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table and column names for Adverts
    public static final String ADVERTS_TABLE = "ADVERTS_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_PHONE = "PHONE";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_LOCATION = "LOCATION";
    public static final String COLUMN_IS_LOST = "IS_LOST";
    public static final String COLUMN_LATITUDE = "LATITUDE";
    public static final String COLUMN_LONGITUDE = "LONGITUDE";

    // Constructor to define database
    public DatabaseHelper(@Nullable Context context) {
        super(context, "lostandfound.db", null, 1);
    }

    // Create the Adverts table in the SQLite database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + ADVERTS_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " INTEGER, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_IS_LOST + " INTEGER, " +
                COLUMN_LATITUDE + " REAL, " +
                COLUMN_LONGITUDE + " REAL)";
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

        // Add the date in the correct format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(advert.getDate());
        cv.put(COLUMN_DATE, formattedDate);

        cv.put(COLUMN_LOCATION, advert.getLocation());
        cv.put(COLUMN_IS_LOST, advert.isLost() ? 1 : 0);
        cv.put(COLUMN_LATITUDE, advert.getLatitude());
        cv.put(COLUMN_LONGITUDE, advert.getLongitude());

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
                int phone = cursor.getInt(2);
                String description = cursor.getString(3);
                String dateString = cursor.getString(4);
                String location = cursor.getString(5);
                boolean isLost = cursor.getInt(6) == 1;
                double latitude = cursor.getDouble(7);   // Get LATITUDE
                double longitude = cursor.getDouble(8);  // Get LONGITUDE

                Advert advert = new Advert(name, phone, description, dateString, location, isLost, latitude, longitude);
                adverts.add(advert);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return adverts;
    }

    //Remove an advert from the database by name
    public boolean removeAdvertByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_NAME + "=?";
        String[] whereArgs = {name};
        int rowsDeleted = db.delete(ADVERTS_TABLE, whereClause, whereArgs);
        return rowsDeleted > 0;
    }

    //Retrieve an advert from the database by name
    public Advert getAdvertByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Advert advert = null;

        String queryString = "SELECT * FROM " + ADVERTS_TABLE + " WHERE " + COLUMN_NAME + "=?";
        Cursor cursor = db.rawQuery(queryString, new String[]{name});

        if (cursor.moveToFirst()) {
            String advertName = cursor.getString(1);
            int phone = cursor.getInt(2);
            String description = cursor.getString(3);
            String dateString = cursor.getString(4);
            String location = cursor.getString(5);
            boolean isLost = cursor.getInt(6) == 1;
            double latitude = cursor.getDouble(7);   // Get LATITUDE
            double longitude = cursor.getDouble(8);  // Get LONGITUDE

            advert = new Advert(advertName, phone, description, dateString, location, isLost, latitude, longitude);
        }

        cursor.close();
        return advert;
    }
}
