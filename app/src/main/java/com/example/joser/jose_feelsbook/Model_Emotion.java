package com.example.joser.jose_feelsbook;

import android.content.Context;
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
import java.util.Collections;

/**
 * This will hold all of the model information for the emotions
 */
public class Model_Emotion {
    private static final String FILENAME = "file.sav";
    public static ArrayList<Emotion> emotions;
    public static Emotion emotionEdit;
    public static int emotionEditListPostion;
    public static ArrayAdapter<Emotion> listAdapter;


    /**
     * Taken from the lonelyTwitter application that was modified in the lab under the guidance
     * of Shaiful Showdury*/
    public static void loadEmotionsFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr); // this way prevents it from reading just
            // characters
            Gson gson = new Gson();
            Type typeList = new TypeToken<ArrayList<Emotion>>(){}.getType();
            emotions = gson.fromJson(reader, typeList);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void saveEmotionsInFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,0);
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

    public static void editEmotion(){
        emotions.set(emotionEditListPostion, emotionEdit);
    }

    public static void deleteEmotion(int index) {
        // remove the emotions from the arraylist
        emotions.remove(index);
    }

    public static void sortEmotionbyDate() {
        Collections.sort(emotions, Collections.<Emotion>reverseOrder());
    }
}
