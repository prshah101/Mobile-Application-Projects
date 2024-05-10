package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalizedlearningexperienceapp.Models.QuizResponse;

import java.util.ArrayList;
import java.util.List;

public class DoQuiz extends AppCompatActivity {
    TextView allTasksTitle1;
    TextView allTasksTitle2;
    TextView allTasksTitle3;

    RadioGroup optionsRg;
    RadioGroup optionsRg2;
    TextView questionTv;

    TextView questionTv2;
    Button submitBtn;

    String choice1;
    String choice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doquiz);

        allTasksTitle1 = findViewById(R.id.allTasksTitle1);
        allTasksTitle2 = findViewById(R.id.allTasksTitle2);
        allTasksTitle3 = findViewById(R.id.allTasksTitle3);
        questionTv2 = findViewById(R.id.questionTv2);
        questionTv = findViewById(R.id.questionTv);
        submitBtn = findViewById(R.id.submitBtn);



        // Retrieve the Intent
        Intent intent = getIntent();
        // Retrieve the SerialisedArrayList extra data
        ArrayList<QuizResponse.QuizResults> dataList = (ArrayList<QuizResponse.QuizResults>) intent.getSerializableExtra("selectedTopicData");
        allTasksTitle1.setText("Generated Task " + 1);
        allTasksTitle2.setText(String.format("About %s", dataList.get(1).getCategory()));

        //Toast.makeText(this, dataList.get(1).getQuestion(), Toast.LENGTH_LONG).show();

        // For the first RadioGroup
        optionsRg = findViewById(R.id.optionsRg);
        RadioButton option1 = findViewById(R.id.option1);
        option1.setText(dataList.get(1).getCorrect_answer());
        questionTv.setText(dataList.get(1).getQuestion());

        List<String> incorrectAnswers = dataList.get(1).getIncorrect_answers();
        RadioButton option2 = findViewById(R.id.option2);
        RadioButton option3 = findViewById(R.id.option3);

        int count = 2;
        for (String answer : incorrectAnswers) {
            if (count == 2){
                option2.setText(answer);
                count--;
            }
            else if (count == 1){
                option3.setText(answer);
                count--;
            }
        }

        // For the second RadioGroup
        optionsRg2 = findViewById(R.id.optionsRg2);
        questionTv2.setText(dataList.get(2).getQuestion());
        RadioButton option11 = findViewById(R.id.option11);
        option11.setText(dataList.get(2).getCorrect_answer());
        RadioButton option22 = findViewById(R.id.option22);
        RadioButton option33 = findViewById(R.id.option33);

        incorrectAnswers = dataList.get(2).getIncorrect_answers();
        count = 2;
        for (String answer : incorrectAnswers) {
            if (count == 2){
                option22.setText(answer);
                count--;
            }
            else if (count == 1){
                option33.setText(answer);
                count--;
            }
        }

        // on below line we are adding check change listener for our radio group.
        optionsRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // on below line we are getting radio button from our group.
                RadioButton radioButton = findViewById(checkedId);

                // on below line we are displaying a toast message.
                choice1 = (String) radioButton.getText();
            }
        });

        optionsRg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // on below line we are getting radio button from our group.
                RadioButton radioButton = findViewById(checkedId);

                // on below line we are displaying a toast message.
                choice2 = (String) radioButton.getText();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoQuiz.this, QuizResults.class);
                intent.putExtra("choice1", choice1);
                intent.putExtra("choice2", choice2);
                startActivity(intent);
            }
        });
    }
}
