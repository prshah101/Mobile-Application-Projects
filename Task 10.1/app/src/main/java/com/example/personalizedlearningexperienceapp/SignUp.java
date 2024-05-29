package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Toast;
public class SignUp extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailValue;
    private EditText confirmEmailValue;

    private EditText passwordValue;
    private EditText confirmPasswordValue;
    private EditText phoneNumberValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        // Find EditText views
        usernameEditText = findViewById(R.id.usernameValue);
        emailValue = findViewById(R.id.emailValue);
        confirmEmailValue = findViewById(R.id.confirmEmailValue);
        passwordValue = findViewById(R.id.passwordValue);
        confirmPasswordValue = findViewById(R.id.confirmPasswordValue);
        phoneNumberValue = findViewById(R.id.phoneNumberValue);

        // Find views
        Button createAccountBtn = findViewById(R.id.createAccountBtn);

        // Set up click listener for the create account button
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text entered in password and confirm password fields into string format
                String password = passwordValue.getText().toString();
                String confirmPassword = confirmPasswordValue.getText().toString();

                String email = emailValue.getText().toString();
                String confirmEmail = confirmEmailValue.getText().toString();

                // Check if passwords match
                if (!password.equals(confirmPassword)) {
                    // Passwords don't match, show error message
                    Toast.makeText(SignUp.this, "Error: Passwords do not match", Toast.LENGTH_SHORT).show();
                }else if(!email.equals(confirmEmail)){
                    // Emails don't match, show error message
                    Toast.makeText(SignUp.this, "Error: Emails do not match", Toast.LENGTH_SHORT).show();
                } else {
                    // Get the username entered
                    String username = usernameEditText.getText().toString();

                    // Check if username already exists in the database
                    DatabaseHelper databaseHelper = new DatabaseHelper(SignUp.this);
                    if (databaseHelper.getUserByUsername(username) != null) {
                        // Username already exists, show toast to prompt user to change the username
                        Toast.makeText(SignUp.this, "Username already exists. Please choose a different one.", Toast.LENGTH_SHORT).show();
                    } else {
                        // Else username is unique, proceed with adding login details to the database
                        // Create a LoginDetails instance
                        UserDetails userDetails = new UserDetails(username, passwordValue.getText().toString(), emailValue.getText().toString(), (int) Long.parseLong(phoneNumberValue.getText().toString()));

                        // Access the addOneLogin() method in the database to add the loginDetails to the database
                        boolean success = databaseHelper.addOneUser(userDetails);
                        // Finish the current activity (SignUpActivity) and return to MainActivity
                        //finish();

                        // If username and password exist in the database, navigate to Home activity
                        Intent intent = new Intent(SignUp.this, SignUpInterestActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
