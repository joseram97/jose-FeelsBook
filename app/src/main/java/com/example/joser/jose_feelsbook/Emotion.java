/**
 * This class takes code from the MOOD class from joseram97/lonelytwitter
 * which was written by me, under the guidance of the lab TA that was presenting
 * shaiful chowdhury
 *
 *
 * FIGURE OUT IF THERE NEEDS TO BE MORE TO THIS*/
package com.example.joser.jose_feelsbook;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Public class emotion. It will represent the emotion and carry the comments and date of the
 * emotion to be viewed later*/
public class Emotion implements Comparable<Emotion>, Serializable {
    private Date TimeStamp;
    private String comment;
    private String type; // Can be ANGER, SADNESS, JOY, FEAR, SURPRISE, and LOVE

    public Emotion(String type, String comment) {
        // Set the default date of the emotion
        this.TimeStamp = new Date();
        this.type = type;
        this.comment = comment;

    }

    public Emotion(String type, String comment, Date date){
        this.TimeStamp = date;
        this.type = type;
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.TimeStamp = date;
    }

    public Date getDate(){
        return this.TimeStamp;
    }

    // Setting comments to the emotion object for use in a list
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return this.comment;
    }

    public void setEmotionType(String type) {
        this.type = type;
    }

    public String getEmotionType() {
        return this.type;
    }

    // Idea taken from https://eclass.srv.ualberta.ca/mod/forum/discuss.php?d=1045897
    // 2018-10-03 6:09PM
    /**
     * Compares the type of this emotion to that of another
     *
     * @params Emotion emotion
     * @returns boolean*/
    public boolean Matches(Emotion emotion){
        if(this.type.equals(emotion.type)) {
            return true;
        }
        else{
            return false;
        }
    }

    // idea of sorting the emotions taken from
    // https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
    // 2018-10-04 9:56PM
    @Override
    public int compareTo(Emotion o) {
        return this.getDate().compareTo(o.getDate());
    }

    public String toString() {
        //set up the format string for the date
        // formats were taken from https://developer.android.com/reference/java/text/SimpleDateFormat
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("MDT"));
        if (this.comment.equals("")){
            return "I feel " + this.type + " | " + format.format(this.TimeStamp);
        }
        else {
            // there is a comment with this emotions
            return "I feel " + this.type + " | " + format.format(this.TimeStamp) +
                    " | \nComment:" + this.comment;
        }
    }

}
