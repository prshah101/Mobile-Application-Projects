package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.userName);

    }

    public void onStartButtonClick(View view) {
        if(userName.getText().toString().isEmpty()) {
            userName.setError("Please enter your name");
            return;
        }

        User user = User.getInstance();
        user.setName(userName.getText().toString());

        // Logic here
        startMainActivity2();
    }

    private void startMainActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("userName", userName.getText().toString()); // pass the user name to the next activity
        startActivity(intent);
    }
}
