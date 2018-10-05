package com.example.joser.jose_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EmotionHistoryActivity extends AppCompatActivity {
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
        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // this will allow the edit pop-up dialog show
                // idea taken from:
                // https://stackoverflow.com/questions/7073577/how-to-get-object-from-listview-in-setonitemclicklistener-in-android
                Model_Emotion.emotionEdit = (Emotion) parent.getAdapter().getItem(position);
                Model_Emotion.emotionEditListPostion = position;
                Intent popup = new Intent(EmotionHistoryActivity.this, EmotionEditPopUp.class);
                startActivity(popup);
            }
        });
    }

    @Override
    protected void onStart() {
        // set up the array adapter and the list of emotions to the list
        super.onStart();
        Model_Emotion.loadEmotionsFromFile(this);
        Model_Emotion.sortEmotionbyDate();
        Model_Emotion.listAdapter = new ArrayAdapter<Emotion>(this, R.layout.history_item, Model_Emotion.emotions);
        historyList.setAdapter(Model_Emotion.listAdapter);
    }
}
