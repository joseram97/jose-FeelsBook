package com.example.joser.jose_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

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

public class HomeActivity extends AppCompatActivity {
    // have a list of all of the global variables that will be used for the application
    public static final String FILENAME = "file.sav";
    public ArrayList<Emotion> emotions = new ArrayList<Emotion>();
    // Use this adapter for the history of the emotions public ArrayAdapter<Emotion> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Here we are going to load all of the buttons showing all the emotions that can be added
        // to the emotion history list
    }

    //@Override
    //protected void onStart() {
        // Here we are going to set the adapter for the scroll view to update
    //}

    /**
     * Taken from the lonelyTwitter application that was modified in the lab under the guidance
     * of Shaiful Showdury*/
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr); // this way prevents it from reading just
            // characters
            Gson gson = new Gson();
            Type typeListTweets = new TypeToken<ArrayList<Emotion>>(){}.getType();
            emotions = gson.fromJson(reader, typeListTweets);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(emotions, osw); // with this gson instance will convert to json, use it on tweets
            // and will write as a string
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
