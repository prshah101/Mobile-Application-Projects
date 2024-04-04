package com.example.task41;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TASK_TABLE = "TASK_TABLE";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_DUEDATE = "DUEDATE";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "tasks.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TASK_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_DUEDATE + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(ToDoItem todoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, todoItem.getTitle());
        cv.put(COLUMN_DESCRIPTION, todoItem.getDescription());
        cv.put(COLUMN_DUEDATE, todoItem.getDueDate());

        long insert = db.insert(TASK_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateOne(ToDoItem todoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, todoItem.getTitle());
        cv.put(COLUMN_DESCRIPTION, todoItem.getDescription());

        // Specify the WHERE clause to update the specific record based on the title
        String selection = COLUMN_TITLE + " = ?";
        String[] selectionArgs = {todoItem.getTitle()};

        int updatedRows = db.update(TASK_TABLE, cv, selection, selectionArgs);

        // Check if any rows were updated
        if (updatedRows == 0) {
            return false; // No rows were updated, so return false
        } else {
            return true; // At least one row was updated, so return true
        }
    }

    public boolean deleteOne(ToDoItem todoItem) {
        // find ToDoItem in the database. if it is found, delete it and return true.
        // if it is not found, return false

        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_TITLE + " = ?";
        String[] whereArgs = { todoItem.getTitle() };

        int rowsAffected = db.delete(TASK_TABLE, whereClause, whereArgs);

        return rowsAffected > 0;
    }


    public List<ToDoItem> getAll() {
        List<ToDoItem> returnList = new ArrayList<>();

        // get data from the database
        String queryString = "SELECT * FROM " + TASK_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects. Put them into the return List
            do {
                String taskTitle = cursor.getString(1);
                String taskDescription = cursor.getString(2);
                String taskDueDate = cursor.getString(3);

                ToDoItem todoItem = new ToDoItem(taskTitle, taskDescription, taskDueDate);
                returnList.add(todoItem);
            } while (cursor.moveToNext());
        } else {
            // failure. do not add anything to the list.
        }

        // close both the cursor and the db when done.
        cursor.close();
        db.close();

        return returnList;
    }
}