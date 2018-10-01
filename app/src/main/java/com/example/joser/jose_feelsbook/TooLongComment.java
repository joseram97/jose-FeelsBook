/**
 * This class will help regulate that a comment for any emotion is not too long*/
package com.example.joser.jose_feelsbook;

public class TooLongComment extends Exception {

    // Set up the constructor
    TooLongComment(String message){
        super(message);
    }
}
