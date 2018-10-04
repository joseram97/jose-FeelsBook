/**
 * This class takes code from the MOOD class from joseram97/lonelytwitter
 * which was written by me, under the guidance of the lab TA that was presenting
 * shaiful chowdhury
 *
 *
 * FIGURE OUT IF THERE NEEDS TO BE MORE TO THIS*/
package com.example.joser.jose_feelsbook;

import java.util.Date;

/**
 * Public class emotion. It will represent the emotion and carry the comments and date of the
 * emotion to be viewed later*/
public class Emotion {
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

    // Idea taken from https://eclass.srv.ualberta.ca/mod/forum/discuss.php?d=1045897
    // 2018-10-03 6:09PM
    /**
     * Compares the type of this emotion to that of another
     *
     * @params Emotion emotion
     * @returns boolean*/
    public boolean Matches(Emotion emotion){
        if(this.type == emotion.type){
            return true;
        }
        else{
            return false;
        }
    }

    public String toString() {
        return "I feel " + this.type + " | " + this.TimeStamp + " | \nComment:" + this.comment;
    }

}
