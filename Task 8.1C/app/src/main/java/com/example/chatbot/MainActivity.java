package com.example.chatbot;

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
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText usernameEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.loginBtn);
        usernameEnter = findViewById(R.id.usernameEnter);


//        // Create an instance of UserDetails with the username
//        UserDetails newUser = new UserDetails("johnsmith");
//
//        // Create an instance of DatabaseHelper
//        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this); // Replace 'context' with your actual context
//
//        // Add the user to the database
//        dbHelper.addOneUser(newUser);


        // Set OnClickListener for Login button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and password in correct string format
                String username = usernameEnter.getText().toString();

                // Create an instance of DatabaseHelper to access the database
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                // Check if the username exists in the database
                UserDetails isValidLogin = databaseHelper.getUserByUsername(username);

                if (isValidLogin != null) {
                    // If username exists in the database, navigate to Chat activity
                    Intent intent = new Intent(MainActivity.this, Chat.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    // Else username isn't in database, show an error message
                    Toast.makeText(MainActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}