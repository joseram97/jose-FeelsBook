package com.example.joser.jose_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmotionHistoryActivity extends AppCompatActivity {
    private ArrayAdapter<Emotion> emotionAdapter;
    private ListView historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_history);

        // Here this activity will show a list of all of the emotions that have been added to the
        // emotion list
        historyList = (ListView) findViewById(R.id.HistoryEmotions);

        // Idea taken from:
        // https://stackoverflow.com/questions/8846707/how-to-implement-a-long-click-listener-on-a-listview
        // 2018-10-04 10:41PM
        historyList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // this will allow the edit pop-up dialog show
                Intent popup = new Intent(getApplicationContext(), EmotionEditPopUp.class);
                startActivity(popup);
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        // set up the array adapter and the list of emotions to the list
        super.onStart();
        Model_Emotion.loadEmotionsFromFile(this);
        Model_Emotion.sortEmotionbyDate();
        emotionAdapter = new ArrayAdapter<Emotion>(this, R.layout.history_item, Model_Emotion.emotions);
        historyList.setAdapter(emotionAdapter);
    }
}
