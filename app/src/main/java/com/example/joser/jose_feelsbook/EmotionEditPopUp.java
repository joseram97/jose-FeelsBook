package com.example.joser.jose_feelsbook;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/***
 * This will be the pop-up window when the user is trying to edit the emotion record
 * Used from this link: https://www.youtube.com/watch?v=eX-TdY6bLdg
 */
public class EmotionEditPopUp extends Activity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private final Calendar editDateCalendar = Calendar.getInstance();
    private SimpleDateFormat formatDate = new SimpleDateFormat("MMM dd, yyyy");
    private SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm aa");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_edit_pop_up);
        TextView emotionView = (TextView) findViewById(R.id.editEmotionTextView);
        final EditText emotionComments = (EditText) findViewById(R.id.editCommentsEmotion);
        TextView emotionDate = (TextView) findViewById(R.id.showEmotionDate);
        TextView emotionTime = (TextView) findViewById(R.id.showEmotionTime);

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

        // set up the buttons to show the properties of the emotion


        emotionView.setText(Model_Emotion.emotionEdit.getEmotionType());
        emotionComments.setText(Model_Emotion.emotionEdit.getComment());
        formatDate.setTimeZone(TimeZone.getTimeZone("MDT"));
        formatTime.setTimeZone(TimeZone.getTimeZone("MDT"));
        emotionDate.setText(formatDate.format(Model_Emotion.emotionEdit.getDate()));
        emotionTime.setText(formatTime.format(Model_Emotion.emotionEdit.getDate()));
        editDateCalendar.setTime(Model_Emotion.emotionEdit.getDate());

        Button delete_btn = (Button) findViewById(R.id.EmotionDeleteBtn);
        Button edit_btn = (Button) findViewById(R.id.EmotionEditBtn);

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when the delete button is pressed, the emotion is removed
                Model_Emotion.deleteEmotion(Model_Emotion.emotionEditListPostion);
                Model_Emotion.listAdapter.notifyDataSetChanged();
                Model_Emotion.saveEmotionsInFile(EmotionEditPopUp.this);
                finish();
            }
        });

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // let the emotion be edited and reinserted into the arraylist
                // set up the date

                Model_Emotion.emotionEdit.setComment(emotionComments.getText().toString());
                Model_Emotion.editEmotion();
                Model_Emotion.sortEmotionbyDate();
                Model_Emotion.listAdapter.notifyDataSetChanged();
                Model_Emotion.saveEmotionsInFile(EmotionEditPopUp.this);
                finish();
            }
        });

        emotionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Taken from: https://developer.android.com/guide/topics/ui/controls/pickers
                // bring up the date picker dialog
                int year = editDateCalendar.get(Calendar.YEAR);
                int month = editDateCalendar.get(Calendar.MONTH);
                int day = editDateCalendar.get(Calendar.DAY_OF_MONTH);

                //create a new date picker dialog
                DatePickerDialog dialog = new DatePickerDialog(EmotionEditPopUp.this, EmotionEditPopUp.this, year, month, day);
                dialog.show();

            }
        });

        emotionTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Taken from https://developer.android.com/guide/topics/ui/controls/pickers
                // bring up the timepicker dialog
                int hour = editDateCalendar.get(Calendar.HOUR_OF_DAY);
                int min = editDateCalendar.get(Calendar.MINUTE);

                // create the time picker dialog
                // Taken from https://developer.android.com/reference/android/app/TimePickerDialog
                TimePickerDialog dialog = new TimePickerDialog(EmotionEditPopUp.this, EmotionEditPopUp.this, hour, min, false);
                dialog.show();
            }
        });

    }


    // set up the listeners for the date and time picker
    // idea from: https://www.youtube.com/watch?v=hwe1abDO2Ag
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        editDateCalendar.set(Calendar.YEAR, year);
        editDateCalendar.set(Calendar.MONTH, month);
        editDateCalendar.set(Calendar.DAY_OF_MONTH, day);
        Date tempDate = editDateCalendar.getTime();
        TextView emotionDate = (TextView) findViewById(R.id.showEmotionDate);
        emotionDate.setText(formatDate.format(tempDate));
        // date has been modified
        Model_Emotion.emotionEdit.setDate(editDateCalendar.getTime());
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        editDateCalendar.set(Calendar.HOUR_OF_DAY, hour);
        editDateCalendar.set(Calendar.MINUTE, minute);
        Date tempDate = editDateCalendar.getTime();
        TextView emotionTime = (TextView) findViewById(R.id.showEmotionTime);
        emotionTime.setText(formatTime.format(tempDate));
        // date has been modified
        Model_Emotion.emotionEdit.setDate(editDateCalendar.getTime());
    }
}
