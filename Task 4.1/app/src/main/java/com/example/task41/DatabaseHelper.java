package com.example.task41;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

// Create a DatabaseHelper class to help manage SQLite database operations
public class DatabaseHelper extends SQLiteOpenHelper {

    // Table and column names
    public static final String TASK_TABLE = "TASK_TABLE";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_DUEDATE = "DUEDATE";
    public static final String COLUMN_ID = "ID";

    // Constructor to initialize the database
    public DatabaseHelper(@Nullable Context context) {
        super(context, "tasks.db", null, 1);
    }

    // Method called when the database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TASK_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_DUEDATE + " TEXT)";
        db.execSQL(createTableStatement);
    }

    // Method called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Method to add a new task to the database
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

    // Method to update an existing task in the database, based on the Title value of the item
    public boolean updateOne(ToDoItem todoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Put updated task details into ContentValues object
        cv.put(COLUMN_TITLE, todoItem.getTitle());
        cv.put(COLUMN_DESCRIPTION, todoItem.getDescription());
        cv.put(COLUMN_DUEDATE, todoItem.getDueDate());

        // Specify the WHERE clause to update the specific record based on the title
        String selection = COLUMN_TITLE + " = ?";
        String[] selectionArgs = {todoItem.getTitle()};

        // Update the record in the database
        int updatedRows = db.update(TASK_TABLE, cv, selection, selectionArgs);

        // Check if any rows were updated
        if (updatedRows == 0) {
            return false; // No rows were updated, so return false
        } else {
            return true; // At least one row was updated, so return true
        }
    }

    // Method to delete a task from the database
    public boolean deleteOne(ToDoItem todoItem) {

        //Access the database in a writable format
        SQLiteDatabase db = this.getWritableDatabase();

        //Find the ToDoItem with the specified Title
        String whereClause = COLUMN_TITLE + " = ?";
        String[] whereArgs = { todoItem.getTitle() };

        // Delete the task from the database
        int rowsAffected = db.delete(TASK_TABLE, whereClause, whereArgs);

        // Check if any rows were affected (so, deleted). If yes, true will be returned, else false
        return rowsAffected > 0;
    }

    // Method to retrieve all tasks from the database
    public List<ToDoItem> getAll() {
        List<ToDoItem> returnList = new ArrayList<>();

        // get data from the database using SQL Query
        String queryString = "SELECT * FROM " + TASK_TABLE;

        //Access the database in a readable format, writable is unecessary
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // Check if the cursor contains any data
        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new ToDoItem objects to read them in the proper manner. Put them into the return List
            do {
                String taskTitle = cursor.getString(1);
                String taskDescription = cursor.getString(2);
                String taskDueDate = cursor.getString(3);

                ToDoItem todoItem = new ToDoItem(taskTitle, taskDescription, taskDueDate);
                returnList.add(todoItem);
            } while (cursor.moveToNext());
        } else {
            // No data found
        }

        // close both the cursor and the db when done.
        cursor.close();
        db.close();

        // Sort the list by DueDate, with soonest dates first. So in acending order
        Collections.sort(returnList, new Comparator<ToDoItem>() {
            @Override
            // Compare ToDoItem objects based on their due dates
            public int compare(ToDoItem item1, ToDoItem item2) {
                //Declare the SimpleDateFormat that will be used (values will be parsed into).
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault());
                try {
                    // Parse the due dates of the two items
                    Date dueDate1 = dateFormat.parse(item1.getDueDate());
                    Date dueDate2 = dateFormat.parse(item2.getDueDate());
                    // Compare due dates and return the result
                    return dueDate1.compareTo(dueDate2);
                } catch (ParseException e) {
                    e.printStackTrace(); //For debugging print the stack
                    return 0; // Return 0 if there's a parsing error
                }
            }
        });

        //Return with the list of ToDoItems in the Database
        return returnList;
    }
}