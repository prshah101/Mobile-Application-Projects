package com.example.translationapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // creating variables for our image view,
    // text view and two buttons.
    EditText edtLanguage;
    TextView translatedTV;
    Button translateLanguageBtn;

    ImageView mic;

    //String[] fromlanguage = LanguageOptions.fromlanguage;
    //String[] tolanguage = LanguageOptions.tolanguage;

    //https://firebaseopensource.com/projects/firebase/firebaseui-web/languages/
    //https://firebase.google.com/docs/reference/android/com/google/firebase/ml/naturallanguage/translate/FirebaseTranslateLanguage
    String[] fromLanguages = {"From", "English","Arabic", "Bulgarian", "Catalan", "Czech", "Hindi", "Farsi", "French", "Japanese", "Spanish", "Turkish", "Chinese"};

    String[] toLanguages = {"To", "English","Arabic", "Bulgarian", "Catalan", "Czech", "Hindi", "Farsi", "French", "Japanese", "Spanish", "Turkish", "Chinese"};

    // create a variable for our
    // firebase language translator.
    FirebaseTranslator englishGermanTranslator;

    private static final int REQUEST_PERMISSION_CODE = 1;
    int languageCode, fromLanguageCode, toLanguageCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner fromSpinner = findViewById(R.id.idFromSpinner);
        Spinner toSpinner = findViewById(R.id.idToSpinner);
        edtLanguage = findViewById(R.id.idEdtLanguage);
        translatedTV = findViewById(R.id.idTVTranslatedLanguage);
        mic = findViewById(R.id.choseSpeaking);

        Button translateLanguageBtn = findViewById(R.id.idBtnTranslateLanguage);

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromLanguageCode = getLanguageCode(fromLanguages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.spinner_item, fromLanguages);

        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromAdapter);

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toLanguageCode = getLanguageCode(toLanguages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.spinner_item, toLanguages);

        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toAdapter);

//        // adding on click listener for button
        translateLanguageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //translatedTV.setText("");
                //Toast.makeText(MainActivity.this, "Input Language: " + Integer.toString(fromLanguageCode), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Output Language: " + Integer.toString(toLanguageCode), Toast.LENGTH_SHORT).show();
                if (edtLanguage.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your text to translate", Toast.LENGTH_SHORT).show();
                } else if(fromLanguageCode == 0) {
                    Toast.makeText(MainActivity.this, "Please select language of the input text", Toast.LENGTH_SHORT).show();
                } else if(toLanguageCode == 0) {
                    Toast.makeText(MainActivity.this, "Please select the language to translate into", Toast.LENGTH_SHORT).show();
                }
                else{
                    translateText(fromLanguageCode, toLanguageCode, edtLanguage.getText().toString());
                }
            }
        });

        mic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to convert into text");
                try {
                    startActivityForResult(i, REQUEST_PERMISSION_CODE);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                edtLanguage.setText(result.get(0));
            }
        }
    }

    public int getLanguageCode(String language) {

        int languageCode = 0;

        switch (language) {

            case "English":
                languageCode = FirebaseTranslateLanguage.EN;
                break;
            case "Chinese":
                languageCode = FirebaseTranslateLanguage.ZH;
                break;
            case "Farsi":
                languageCode = FirebaseTranslateLanguage.FA;
                break;
            case "French":
                languageCode = FirebaseTranslateLanguage.FR;
                break;
            case "Japanese":
                languageCode = FirebaseTranslateLanguage.JA;
                break;
            case "Spanish":
                languageCode = FirebaseTranslateLanguage.ES;
                break;
            case "Turkish":
                languageCode = FirebaseTranslateLanguage.TR;
                break;
            case "Catalan":
                languageCode = FirebaseTranslateLanguage.CA;
                break;
            case "Bulgarian":
                languageCode = FirebaseTranslateLanguage.BG;
                break;

            case "Czech":
                languageCode = FirebaseTranslateLanguage.CS;
                break;

            case "Hindi":
                languageCode = FirebaseTranslateLanguage.HI;
                break;
            case "Arabic":
                languageCode = FirebaseTranslateLanguage.AR;
                break;
            default:
        }
        return languageCode;
    }

    private void translateText(int fromLanguageCode, int toLanguageCode, String source) {
        translatedTV.setText("Downloading Model..");
        FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
                .setSourceLanguage(fromLanguageCode)
                .setTargetLanguage(toLanguageCode)
                .build();

        FirebaseTranslator translator = FirebaseNaturalLanguage.getInstance().getTranslator(options);
        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().build();

        translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                translatedTV.setText("Translating..");
                translator.translate(source).addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        translatedTV.setText(s);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Fail to translate: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Fail to download language Model " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
