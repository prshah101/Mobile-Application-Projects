package com.example.itubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //Start SignUpActivity when signUpBtn is clicked
                 Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                 startActivity(intent);
            }
        });
    }
}