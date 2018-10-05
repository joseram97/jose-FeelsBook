package com.example.joser.jose_feelsbook;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/***
 * This will be the pop-up window when the user is trying to edit the emotion record
 * Used from this link: https://www.youtube.com/watch?v=eX-TdY6bLdg
 */
public class EmotionEditPopUp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_edit_pop_up);

        // get the display metrics for the edit popup window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int activity_width = dm.widthPixels;
        int activity_height = dm.heightPixels;

        // set the layout width and height dimensions
        getWindow().setLayout((int) (activity_width*0.8), (int) (activity_height*0.8));

        // set up the layout parameters for consistency
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        Button delete_btn = (Button) findViewById(R.id.EmotionDeleteBtn);
        Button edit_btn = (Button) findViewById(R.id.EmotionDeleteBtn);

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when the delete button is pressed, the emotion is removed

            }
        });

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // let the emotion be edited and reinserted into the arraylist
                finish();
                return;
            }
        });

    }
}
