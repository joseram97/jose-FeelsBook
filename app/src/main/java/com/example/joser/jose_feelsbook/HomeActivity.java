package com.example.joser.jose_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

// For implementation of View.OnClickListener I took idea from
// https://stackoverflow.com/questions/25905086/multiple-buttons-onclicklistener-android
// 2018-10-03 6:27PM
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    // have a list of all of the global variables that will be used for the application
//    private static final String FILENAME = "file.sav";
//    private ArrayList<Emotion> emotions = new ArrayList<Emotion>();
    private ScrollView emotionsView;
    // Use this adapter for the history of the emotions public ArrayAdapter<Emotion> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Here we are going to load all of the buttons showing all the emotions that can be added
        // to the emotion history list
        // Set up all of the listener buttons
        Button angerButton = (Button) findViewById(R.id.AngerButton);
        Button loveButton = (Button) findViewById(R.id.LoveButton);
        Button surpriseButton = (Button) findViewById(R.id.SurpriseButton);
        Button fearButton = (Button) findViewById(R.id.FearButton);
        Button sadButton = (Button) findViewById(R.id.SadButton);
        Button joyButton = (Button) findViewById(R.id.JoyButton);
        Button viewHistory = (Button) findViewById(R.id.HistoryButton);

        //Setting the onClickListener for all of these buttons
        angerButton.setOnClickListener(this);
        loveButton.setOnClickListener(this);
        surpriseButton.setOnClickListener(this);
        fearButton.setOnClickListener(this);
        sadButton.setOnClickListener(this);
        joyButton.setOnClickListener(this);
        viewHistory.setOnClickListener(this);

        emotionsView = (ScrollView) findViewById(R.id.EmotionScrollView);
    }

    @Override
    protected void onStart() {
        // Here we are going to set the adapter for the scroll view to update
        super.onStart();
        Model_Emotion.loadEmotionsFromFile(this);
        if (Model_Emotion.emotions == null){
            Model_Emotion.emotions = new ArrayList<Emotion>();
        }
        updateButtons("all");
    }

    @Override
    public void onClick(View view) {
        boolean isHistoryActivity = false;
        // get the comment text just in case
        EditText commentTextEdit = (EditText) findViewById(R.id.CommentTextEdit);
        String comment = commentTextEdit.getText().toString();
        switch (view.getId()) {

            case R.id.AngerButton:
                Emotion angerEmotion = new Emotion("ANGER", comment);
                Model_Emotion.emotions.add(angerEmotion);
                // get the count of anger emotions and set the text of the button to that

                break;

            case R.id.LoveButton:
                Model_Emotion.emotions.add(new Emotion("LOVE", comment));
                break;

            case R.id.SurpriseButton:
                Model_Emotion.emotions.add(new Emotion("SURPRISE", comment));
                break;

            case R.id.JoyButton:
                Model_Emotion.emotions.add(new Emotion("JOY", comment));
                break;

            case R.id.FearButton:
                Model_Emotion.emotions.add(new Emotion("FEAR", comment));
                break;

            case R.id.SadButton:
                Model_Emotion.emotions.add(new Emotion("SADNESS", comment));
                break;

            case R.id.HistoryButton:
                // we are going to change the activity
                isHistoryActivity = true;
                break;
        }

        if (isHistoryActivity){
            //change the activity
            Intent intent = new Intent(this, EmotionHistoryActivity.class);
            startActivity(intent);
        }
        else{
            // one of the emotion buttons has been pressed and the scroll layout needs to be
            // updated (to update the count and such)
            updateButtons("all");
            emotionsView.invalidate();
            emotionsView.requestLayout();
            Model_Emotion.saveEmotionsInFile(this);
        }
    }

    /**
     * Update all of the buttons within the scroll view when there are changes within the array
     */
    public void updateButtons(String button) {
        // initialize all the buttons
        Button angerButton = (Button) findViewById(R.id.AngerButton);
        Button loveButton = (Button) findViewById(R.id.LoveButton);
        Button surpriseButton = (Button) findViewById(R.id.SurpriseButton);
        Button fearButton = (Button) findViewById(R.id.FearButton);
        Button sadButton = (Button) findViewById(R.id.SadButton);
        Button joyButton = (Button) findViewById(R.id.JoyButton);
        Button viewHistory = (Button) findViewById(R.id.HistoryButton);

        Emotion anger = new Emotion("ANGER", "");
        Emotion sadness = new Emotion("SADNESS", "");
        Emotion joy = new Emotion("JOY", "");
        Emotion surprise = new Emotion("SURPRISE", "");
        Emotion fear = new Emotion("FEAR", "");
        Emotion love = new Emotion("LOVE", "");

        switch (button){
            case "all":
                angerButton.setText("ANGER("+
                        Integer.toString(FeelsController.countEmotion(anger, Model_Emotion.emotions)) + ")");
                sadButton.setText("SAD("+
                        Integer.toString(FeelsController.countEmotion(sadness, Model_Emotion.emotions)) + ")");
                joyButton.setText("JOY("+
                        Integer.toString(FeelsController.countEmotion(joy, Model_Emotion.emotions)) + ")");
                surpriseButton.setText("SURPRISE("+
                        Integer.toString(FeelsController.countEmotion(surprise, Model_Emotion.emotions)) + ")");
                fearButton.setText("FEAR("+
                        Integer.toString(FeelsController.countEmotion(fear, Model_Emotion.emotions)) + ")");
                loveButton.setText("LOVE("+
                        Integer.toString(FeelsController.countEmotion(love, Model_Emotion.emotions)) + ")");
        }
    }

}
