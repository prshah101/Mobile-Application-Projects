package com.example.lostandfoundv2;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewAdvertActivity extends AppCompatActivity {

    private TextView advertTitle;
    private RadioGroup postTypeRadioGroup;
    private RadioButton lostBtn;
    private RadioButton foundBtn;
    private EditText nameEt;
    private EditText phoneEt;
    private EditText descriptionEt;
    private TextView dateUserTv;
    private EditText locationEt;

    private Button saveBtn;

    private Button backBtn1;

    private Calendar calendar;

    double latitude;

    String location;

    double longitude;
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
        dateUserTv = findViewById(R.id.dateUserTv);
        saveBtn = findViewById(R.id.saveBtn);
        backBtn1 = findViewById(R.id.backBtn1);

        // Initialize Calendar instance
        calendar = Calendar.getInstance();

        // Set OnClickListener for the date EditText field
        dateUserTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Set OnClickListener for save button, then the advert is added to the database (if proper values were entered by user)
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAdvertToDatabase();
            }
        });

        // Set OnClickListener for the back button. which leads back to the Main activity page
        backBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewAdvertActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        String apiKey = getString(R.string.api_key);

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);


        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                // Get the LatLng object
                LatLng latLng = place.getLatLng();
                if (latLng != null) {
                    latitude = latLng.latitude;
                    longitude = latLng.longitude;
                    Log.i(TAG, "Latitude: " + latitude + ", Longitude: " + longitude);
                }
                location =  place.getName();
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

    // Method to show DatePickerDialog
    private void showDatePickerDialog() {
        // Create a DatePickerDialog and set the current date as default
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Set the selected date in the EditText field
                        calendar.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        String selectedDate = dateFormat.format(calendar.getTime());
                        dateUserTv.setText(selectedDate);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        // Show the DatePickerDialog
        datePickerDialog.show();
    }


    // Method to add an advert to the database by managing user inputs
    private void addAdvertToDatabase() {
        String name = nameEt.getText().toString().trim();
        String phoneString = phoneEt.getText().toString().trim();
        String description = descriptionEt.getText().toString().trim();
        String dateString = dateUserTv.getText().toString().trim(); // Assuming date format is entered correctly
        boolean isLost = lostBtn.isChecked();

        boolean radioBtnnotSelected = postTypeRadioGroup.getCheckedRadioButtonId() == -1;

        // Check if any of the fields are empty
        if (name.isEmpty() || phoneString.isEmpty() || description.isEmpty() || dateString.isEmpty() || location.isEmpty() || radioBtnnotSelected) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
        }else {
            int phone = 0;
            //Check that the phone number is in the correct format
            try {
                // Try parsing phoneString to integer
                phone = Integer.parseInt(phoneString);
                //Create instance of DatabaseHelper
                DatabaseHelper databaseHelper = new DatabaseHelper(NewAdvertActivity.this);
                // Add advert to the database
                boolean success = databaseHelper.addOneAdvert(new Advert(name, phone, description, dateString, location, isLost, latitude, longitude));
                if (success) {
                    Toast.makeText(this, "Advert added successfully", Toast.LENGTH_SHORT).show();
                    // Clear input fields after successful addition
                    nameEt.setText("");
                    phoneEt.setText("");
                    descriptionEt.setText("");
                    dateUserTv.setText("");
                } else {
                    Toast.makeText(this, "Failed to add advert", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                // Handle the case where phoneString cannot be parsed to an integer
                Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

