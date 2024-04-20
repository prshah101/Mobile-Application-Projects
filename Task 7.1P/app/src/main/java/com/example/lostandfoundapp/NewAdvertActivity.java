package com.example.lostandfoundapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewAdvertActivity extends AppCompatActivity {

    private TextView advertTitle;
    private RadioGroup postTypeRadioGroup;
    private RadioButton lostBtn;
    private RadioButton foundBtn;
    private EditText nameEt;
    private EditText phoneEt;
    private EditText descriptionEt;
    private EditText dateEt;
    private EditText locationEt;

    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_advert);

        // Initialize UI elements
        advertTitle = findViewById(R.id.advertTitle);
        postTypeRadioGroup = findViewById(R.id.postTypeRadioGroup);
        lostBtn = findViewById(R.id.lostBtn);
        foundBtn = findViewById(R.id.foundBtn);
        nameEt = findViewById(R.id.nameEt);
        phoneEt = findViewById(R.id.phoneEt);
        descriptionEt = findViewById(R.id.descriptionEt);
        dateEt = findViewById(R.id.dateEt);
        locationEt = findViewById(R.id.locationEt);
        saveBtn = findViewById(R.id.saveBtn);


        // Set OnClickListener for save button
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAdvertToDatabase();
            }
        });


    }


    // Method to add an advert to the database
    private void addAdvertToDatabase() {
        String name = nameEt.getText().toString().trim();
        String phoneString = phoneEt.getText().toString().trim();
        String description = descriptionEt.getText().toString().trim();
        String dateString = dateEt.getText().toString().trim(); // Assuming date format is entered correctly
        String location = locationEt.getText().toString().trim();
        boolean isLost = lostBtn.isChecked();

        boolean radioBtnnotSelected = postTypeRadioGroup.getCheckedRadioButtonId() == -1;

        // Check if any of the fields are empty
        if (name.isEmpty() || phoneString.isEmpty() || description.isEmpty() || dateString.isEmpty() || location.isEmpty() || radioBtnnotSelected) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
        }else {
            int phone = 0;
            try {
                // Try parsing phoneString to integer
                phone = Integer.parseInt(phoneString);
            } catch (NumberFormatException e) {
                // Handle the case where phoneString cannot be parsed to an integer
                Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
            }

            Date date = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                date = dateFormat.parse(dateString);
            } catch (ParseException e) {
                Toast.makeText(this, "Please enter a valid date", Toast.LENGTH_SHORT).show();
            }

            DatabaseHelper databaseHelper = new DatabaseHelper(NewAdvertActivity.this);
            // Add advert to the database
            boolean success = databaseHelper.addOneAdvert(new Advert(name, phone, description, dateString, location, isLost));
            if (success) {
                Toast.makeText(this, "Advert added successfully", Toast.LENGTH_SHORT).show();
                // Clear input fields after successful addition
                nameEt.setText("");
                phoneEt.setText("");
                descriptionEt.setText("");
                dateEt.setText("");
                locationEt.setText("");
            } else {
                Toast.makeText(this, "Failed to add advert", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

