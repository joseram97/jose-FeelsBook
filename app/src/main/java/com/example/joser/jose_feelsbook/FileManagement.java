package com.example.joser.jose_feelsbook;

import android.content.Context;

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

/**
 * This class will handle all of the reading and writing of data for files located on the android
 * device
 */
public class FileManagement {

    /**
     * Taken from the lonelyTwitter application that was modified in the lab under the guidance
     * of Shaiful Showdury*/
    public static ArrayList<Emotion> loadFromFile(String FILENAME, Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr); // this way prevents it from reading just
            // characters
            Gson gson = new Gson();
            Type typeList = new TypeToken<ArrayList<Emotion>>(){}.getType();
            return gson.fromJson(reader, typeList);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static void saveInFile(String FILENAME, Context context, ArrayList<Emotion> arrayList) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(arrayList, osw); // with this gson instance will convert to json, use it on tweets
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
