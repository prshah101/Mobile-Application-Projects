package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEnter;
    private EditText passwordEnter;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEnter = findViewById(R.id.usernameEnter);
        passwordEnter = findViewById(R.id.passwordEnter);
        loginBtn = findViewById(R.id.loginBtn);
        Button signUpBtn = findViewById(R.id.signUpBtn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SignUp activity when the signup button is clicked
                Intent intent = new Intent(MainActivity.this, SignUp.class);
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
                UserDetails isValidLogin = databaseHelper.getUserByUsernameAndPassword(username, password);

                if (isValidLogin != null) {
                    // If username and password exist in the database, navigate to Home activity
                    Intent intent = new Intent(MainActivity.this, AllTasks.class);
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
