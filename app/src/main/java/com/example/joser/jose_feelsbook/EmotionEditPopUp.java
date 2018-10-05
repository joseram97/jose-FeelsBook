package com.example.joser.jose_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/***
 * This will be the pop-up window when the user is trying to edit the emotion record
 * Used from this link: https://www.youtube.com/watch?v=eX-TdY6bLdg
 */
public class EmotionEditPopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_edit_pop_up);
    }
}
