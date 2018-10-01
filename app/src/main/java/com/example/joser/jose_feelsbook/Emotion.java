/**
 * This class takes code from the MOOD class from joseram97/lonelytwitter
 * which was written by me, under the guidance of the lab TA that was presenting
 * shaiful chowdhury
 *
 *
 * FIGURE OUT IF THERE NEEDS TO BE MORE TO THIS*/
package com.example.joser.jose_feelsbook;

import java.util.Date;

public abstract class Emotion {
    private Date TimeStamp;
    private String comment;

    public Emotion() {
        // Set the default date of the emotion
        this.TimeStamp = new Date();
    }

    public Emotion(Date date){
        this.TimeStamp = date;
    }

    public void setDate(Date date) {
        this.TimeStamp = date;
    }

    public Date getDate(){
        return this.TimeStamp;
    }

    // Setting comments to the emotion object for use in a list
    public void setComments(String comment) throws TooLongComment {
        if (comment.length() > 100){
            throw new TooLongComment("Comment is too long");
        }
        else {
            this.comment = comment;
        }
    }

    public String getComments() {
        return this.comment;
    }
}
