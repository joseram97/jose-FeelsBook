package com.example.joser.jose_feelsbook;

public class FeelsController {
    // This will handle all of the edits that each emotion class may handle
    public <T extends Emotion> T editEmotion(T emotion) {
        return emotion;
    }
}
