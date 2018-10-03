package com.example.joser.jose_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class EmotionHistoryActivity extends AppCompatActivity {
    public ArrayList<Emotion> emotions = new ArrayList<Emotion>();
    public ArrayAdapter<Emotion> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_history);

        // Here this activity will show a list of all of the emotions that have been added to the
        // emotion list
    }

    @Override
    protected void onStart() {
        // set up the array adapter and the list of emotions to the list
        super.onStart();

    }
}
