package com.example.personalizedlearningexperienceapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class UpgradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upgrade_activity);

        // Initialize views
        ImageView backBtnIcon2 = findViewById(R.id.backBtnIcon2);
        TextView upgradeTv1 = findViewById(R.id.upgradeTv1);
        TextView upgradeTv2 = findViewById(R.id.upgradeTv2);
        TextView tvStarter = findViewById(R.id.tvStarter);
        TextView upgradeDescTv = findViewById(R.id.upgradeDescTv);
        CardView purchaseBtnCard = findViewById(R.id.purchaseBtnCard);
        TextView purchaseBtnText = findViewById(R.id.purchaseBtnText);
        TextView tvIntermediate = findViewById(R.id.tvIntermediate);
        TextView upgradeDescTv2 = findViewById(R.id.upgradeDescTv2);
        CardView purchaseBtnCard2 = findViewById(R.id.purchaseBtnCard2);
        TextView purchaseBtnText2 = findViewById(R.id.purchaseBtnText2);
        TextView tvAdvanced = findViewById(R.id.tvAdvanced);
        TextView upgradeDescTv3 = findViewById(R.id.upgradeDescTv3);
        CardView purchaseBtnCard3 = findViewById(R.id.purchaseBtnCard3);
        TextView purchaseBtnText3 = findViewById(R.id.purchaseBtnText3);

        // Set onClickListener for back button
        backBtnIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Set onClickListener for purchase button
        purchaseBtnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle purchase button click for starter upgrade
            }
        });

        // Set onClickListener for purchase button 2
        purchaseBtnCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle purchase button click for intermediate upgrade
            }
        });

        // Set onClickListener for purchase button 3
        purchaseBtnCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle purchase button click for advanced upgrade
            }
        });
    }
}
