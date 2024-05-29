package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SettingsActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // Initialize views
        CardView upgradeBtnCard = findViewById(R.id.upgradeBtnCard);
        CardView shareBtnCard = findViewById(R.id.shareBtnCard);
        TextView settingsUsernameTv = findViewById(R.id.settingsUsernameTv);
        TextView totalQuestionsCountTv = findViewById(R.id.totalQuestionsCountTv);
        TextView correctlyAnsweredCountTv = findViewById(R.id.correctlyAnsweredCountTv);
        TextView incorrectlyAnsweredCountTv = findViewById(R.id.incorrectlyAnsweredCountTv);

        String username = getIntent().getStringExtra("username");
        settingsUsernameTv.setText(username);

        databaseHelper = new DatabaseHelper(SettingsActivity.this); // Initialize DatabaseHelper
        Scores scores = databaseHelper.getScoresByUsername(username);
        int totalScore = scores.getTotalScore();
        int correctScore = scores.getCorrectScore();
        int wrongScore = scores.getWrongScore();

        totalQuestionsCountTv.setText(String.valueOf(totalScore));
        correctlyAnsweredCountTv.setText(String.valueOf(correctScore));
        incorrectlyAnsweredCountTv.setText(String.valueOf(wrongScore));
        upgradeBtnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, UpgradeActivity.class);
                startActivity(intent);
            }
        });

        //Share the details of the user
        shareBtnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, username + " has a score of " + correctlyAnsweredCountTv.getText() + "/" + totalQuestionsCountTv.getText());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });

    }
}
