package com.example.joser.jose_feelsbook;

import java.util.ArrayList;

public class FeelsController {

    /**
     * This function will be used to count how many emotions are within the list of emotions
     * This idea has been taken from https://eclass.srv.ualberta.ca/mod/forum/discuss.php?d=1045897
     */
    public static int countEmotion(Emotion emotion, ArrayList<Emotion> emotions) {
        int num_emotions = 0;
        for (Emotion itr_emotion: emotions){
            if (emotion.Matches(itr_emotion)) {
                num_emotions++;
            }
        }
        return num_emotions;
    }
}
