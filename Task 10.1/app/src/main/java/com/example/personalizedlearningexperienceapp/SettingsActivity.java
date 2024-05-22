package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // Initialize views
        ImageView backBtnIcon = findViewById(R.id.backBtnIcon);
        CardView backBtnCard = findViewById(R.id.backBtnCard);
        CardView upgradeBtnCard = findViewById(R.id.upgradeBtnCard);
        TextView settingsUsernameTv = findViewById(R.id.settingsUsernameTv);
        ImageView profileImage = findViewById(R.id.profileImage);
        ImageView settingsImageView = findViewById(R.id.settingsImageView);
        TextView settingsNotification = findViewById(R.id.settingsNotification);
        TextView totalQuestionsTitleTv = findViewById(R.id.totalQuestionsTitleTv);
        TextView totalQuestionsCountTv = findViewById(R.id.totalQuestionsCountTv);
        TextView correctlyAnsweredQuestionsTitleTv = findViewById(R.id.correctlyAnsweredQuestionsTitleTv);
        TextView correctlyAnsweredCountTv = findViewById(R.id.correctlyAnsweredCountTv);
        TextView incorrectTitleTv = findViewById(R.id.incorrectTitleTv);
        TextView textView4 = findViewById(R.id.textView4);
        ImageView bellImage = findViewById(R.id.bellImage);
        TextView settingsSummarisedTitle = findViewById(R.id.settingsSummarisedTitle);
        TextView SummaryTv = findViewById(R.id.SummaryTv);
        TextView upgradeBtnText = findViewById(R.id.upgradeBtnText);
        ImageView upgradeBtnIcon = findViewById(R.id.upgradeBtnIcon);
        TextView shareBtnText = findViewById(R.id.purchaseBtnText);
        ImageView shareBtnIcon = findViewById(R.id.shareBtnIcon);

        String username = getIntent().getStringExtra("username");
        settingsUsernameTv.setText(username);

        upgradeBtnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, UpgradeActivity.class);
                startActivity(intent);
            }
        });

    }
}
