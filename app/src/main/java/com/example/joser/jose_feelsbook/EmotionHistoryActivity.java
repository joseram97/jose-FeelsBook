package com.example.joser.jose_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EmotionHistoryActivity extends AppCompatActivity {
    private final String FILENAME = "file.sav";
    private ArrayList<Emotion> emotions = new ArrayList<Emotion>();
    private ArrayAdapter<Emotion> emotionAdapter;
    private ListView historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_history);

        // Here this activity will show a list of all of the emotions that have been added to the
        // emotion list
        historyList = (ListView) findViewById(R.id.HistoryEmotions);
    }

    @Override
    protected void onStart() {
        // set up the array adapter and the list of emotions to the list
        super.onStart();
        emotions = FileManagement.loadFromFile(FILENAME, this);
        emotionAdapter = new ArrayAdapter<Emotion>(this, R.layout.history_item, emotions);
        historyList.setAdapter(emotionAdapter);
        boolean test = true;
    }
}
