package com.example.a21p;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    Spinner sp1, sp2; // Declare Spinners
    EditText ed1; // Declare EditText
    Button b1; // Declare Button

    TextView result; // Declare TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge layout
        setContentView(R.layout.activity_main); // Set layout from the XML file

        // Initialize views by using their IDs from the XML file
        ed1 = findViewById(R.id.inputValue);
        sp1 = findViewById(R.id.spinnerFrom);
        sp2 = findViewById(R.id.spinnerTo);
        b1 = findViewById(R.id.button);
        result = findViewById(R.id.resultTextView);

        // Set OnClickListener for the conversion Button
        b1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Double tot; // Declare variable to hold the converted value

                  // Get the user input value from EditText and selected units from Spinners
                  Double amount = Double.parseDouble(ed1.getText().toString());
                  String sourceUnit = sp1.getSelectedItem().toString().toLowerCase();
                  String destinationUnit = sp2.getSelectedItem().toString().toLowerCase();
                  tot = convert(amount, sourceUnit, destinationUnit);  // Call the convert method

                  if (tot != null) { // Check if conversion was successful, because tot wouldn't be null if the convert method worked
                      result.setText(String.valueOf(tot)); // Display converted value in TextView

                  } else { // Show an error message if tot was null
                      result.setText(String.valueOf("Conversion not supported"));
                  }
              }
        });

        // Apply the system window insets to the layout for edge-to-edge effect
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method to perform unit conversion
    private Double convert(double inputValue, String sourceUnit, String destinationUnit) {
        // Length Conversions//
        if (sourceUnit.equals("inch") && destinationUnit.equals("cm")) {
            return inputValue * 2.54;
        } else if (sourceUnit.equals("foot") && destinationUnit.equals("cm")) {
            return inputValue * 30.48;
        } else if (sourceUnit.equals("yard") && destinationUnit.equals("cm")) {
            return inputValue * 91.44;
        } else if (sourceUnit.equals("mile") && destinationUnit.equals("km")) {
            return inputValue * 1.60934;
        }
        // Weight Conversions//
        else if (sourceUnit.equals("pound") && destinationUnit.equals("kg")) {
            return inputValue * 0.453592;
        } else if (sourceUnit.equals("ounce") && destinationUnit.equals("g")) {
            return inputValue * 28.3495;
        } else if (sourceUnit.equals("ton") && destinationUnit.equals("kg")) {
            return inputValue * 907.185;
        }
        // Temperature Conversions//
        else if (sourceUnit.equals("celsius") && destinationUnit.equals("fahrenheit")) {
            return (inputValue * 1.8) + 32;
        } else if (sourceUnit.equals("fahrenheit") && destinationUnit.equals("celsius")) {
            return (inputValue - 32) / 1.8;
        } else if (sourceUnit.equals("celsius") && destinationUnit.equals("kelvin")) {
            return inputValue + 273.15;
        } else if (sourceUnit.equals("kelvin") && destinationUnit.equals("celsius")) {
            return inputValue - 273.15;
        }
        // If units are not recognized or compatible, return null indicating an error.
        return null;
    }
}