package com.ifansdev.flyingsquirrel.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.ifansdev.flyingsquirrel.MyGame;

public class Score extends Actor {
    private MyGame myGame;
    private int screenHeight;

    private Label score;
    private Label hiScore;
    private Label hi;

    private int highScoreMoveY;
    private Integer scoreInteger = 0;
    private int countScore;
    private boolean isHighScore;
    private boolean isGameOver;

    public Score(MyGame myGame, int screenHeight) {
        this.myGame = myGame;
        this.screenHeight = screenHeight;

        score = new Label(Integer.toString(scoreInteger), myGame.getAssets().getSkin());
        score.setPosition(192, screenHeight - 22);
        hi = new Label(Integer.toString(scoreInteger), myGame.getAssets().getSkin());
        hi.setPosition(131, screenHeight - 42);
        hiScore = new Label(Integer.toString(scoreInteger), myGame.getAssets().getSkin());
        highScoreMoveY = screenHeight - 22;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        score.setText(Integer.toString(scoreInteger));
        score.draw(batch, 1);

        if (isGameOver) {
            hi.setText("HI-");
            hi.draw(batch, 1);
            hiScore.setText(Integer.toString(myGame.getSaveScore().getHighScore()));
            hiScore.setPosition(192, screenHeight - 42);
            hiScore.draw(batch, 1);
        }

        if (isHighScore) {
            hi.setText("HI-");
            hi.draw(batch, 1);
            hiScore.setText(Integer.toString(myGame.getSaveScore().getHighScore()));
            if (highScoreMoveY != screenHeight - 42) highScoreMoveY --;
            hiScore.setPosition(192, highScoreMoveY);
            hiScore.draw(batch, 1);
        }
    }

    public void scoreIncrement() {
        scoreInteger++;
    }

    public void checkScore() {
        if (scoreInteger > myGame.getSaveScore().getHighScore()) {
            myGame.getSaveScore().setHighScore(scoreInteger);
            isHighScore = true;

        } else {
            isGameOver = true;
        }

    }

    public void checkScoreLeaderboards() {
        if (myGame.getIForAndroidLauncher() == null) {
            return;
        }

        if (scoreInteger > countScore) {
            countScore = scoreInteger;
            myGame.getIForAndroidLauncher().getMyLeaderboard().pushAccomplishments(countScore);
        }
    }
}
