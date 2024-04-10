package com.example.itubeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        fullNameEditText = findViewById(R.id.editTextText3);
        usernameEditText = findViewById(R.id.editTextText4);
        passwordEditText = findViewById(R.id.editTextText);
        confirmPasswordEditText = findViewById(R.id.editTextText2);
        createAccountBtn = findViewById(R.id.createAccountBtn);

        // Set OnClickListener for createAccountBtn
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity (SignUpActivity) and return to MainActivity
                finish();
            }
        });
    }
}
