package com.ifansdev.flyingsquirrel.assets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SaveScore {

    private Preferences preferences;

    public SaveScore() {
        preferences = Gdx.app.getPreferences("FlyingSquirrel");
        if (!preferences.contains("highScore")) {
            preferences.putInteger("highScore", 0);
        }


    }

    public void setHighScore(int value) {
        preferences.putInteger("highScore", value);
        preferences.flush();
    }

    public int getHighScore() {
        return preferences.getInteger("highScore");
    }

}
