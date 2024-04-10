package com.example.itubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEnter;
    private EditText passwordEnter;
    private Button loginBtn;
    private Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views
        usernameEnter = findViewById(R.id.usernameEnter);
        passwordEnter = findViewById(R.id.passwordEnter);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);

        // Set OnClickListener for SignUp button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //Start SignUpActivity when signUpBtn is clicked
                 Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                 startActivity(intent);
            }
        });

        // Set OnClickListener for Login button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and password in correct string format
                String username = usernameEnter.getText().toString();
                String password = passwordEnter.getText().toString();

                // Create an instance of DatabaseHelper to access the database
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                // Check if the username and password exist in the database
                LoginDetails isValidLogin = databaseHelper.getLoginByUsernameAndPassword(username, password);

                if (isValidLogin != null) {
                    // If username and password exist in the database, navigate to Home activity
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    // Else username and password do not match, show an error message
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}