package com.example.itubeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText fullNameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        // Initialize views
        fullNameEditText = findViewById(R.id.fullNameValue);
        usernameEditText = findViewById(R.id.usernameValue);
        passwordEditText = findViewById(R.id.passwordValue);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordValue);
        createAccountBtn = findViewById(R.id.createAccountBtn);

        // Set OnClickListener for createAccountBtn
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text entered in password and confirm password fields
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // Check if passwords match
                if (!password.equals(confirmPassword)) {
                    // Passwords don't match, show error message
                    Toast.makeText(SignUpActivity.this, "Error: Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    // Get the username entered
                    String username = usernameEditText.getText().toString();

                    // Check if username already exists in the database
                    DatabaseHelper databaseHelper = new DatabaseHelper(SignUpActivity.this);
                    if (databaseHelper.getLoginByUsername(username) != null) {
                        // Username already exists, show toast to create a new one
                        Toast.makeText(SignUpActivity.this, "Username already exists. Please choose a different one.", Toast.LENGTH_SHORT).show();
                    } else {
                        // Username is unique, proceed with adding login details to the database
                        // Create a LoginDetails instance
                        LoginDetails loginDetails = new LoginDetails(fullNameEditText.getText().toString(), username, confirmPassword);

                        // Access the addOne() method in the database to add the loginDetails to the database
                        boolean success = databaseHelper.addOneLogin(loginDetails);

                        // Display relevant Toast based on the success of adding loginDetails to the database
                        if (success) {
                            Toast.makeText(SignUpActivity.this, "Login Details added successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Failed to add Login Details", Toast.LENGTH_SHORT).show();
                        }

                        // Finish the current activity (SignUpActivity) and return to MainActivity
                        finish();
                    }
                }
            }
        });
    }
}
