package com.ifansdev.flyingsquirrel.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.ifansdev.flyingsquirrel.MyGame;
import com.ifansdev.flyingsquirrel.actors.Score;
import com.ifansdev.flyingsquirrel.actors.movingforeground.Flower;
import com.ifansdev.flyingsquirrel.actors.Forest;
import com.ifansdev.flyingsquirrel.actors.Squirrel;
import com.ifansdev.flyingsquirrel.actors.movingforeground.Stone;
import com.ifansdev.flyingsquirrel.assets.Assets;


public class GameScreen extends BaseScreen {

    private Squirrel squirrel;

    private Array<Stone> stoneArray;
    private final int COUNT_STONE = 4;

    private Array<Flower> flowerArray;
    private final int COUNT_FLOWER = 5;

    private final float SPEED = 200f;

    private Score score;

    private Button pausePlayBtn;
    private PauseScreen pauseScreen;

    public GameScreen(final MyGame myGame) {
        super(myGame);

        squirrel = new Squirrel(myGame);

        Image ground = new Image(myGame.getAssets().getTexture(Assets.GROUND));

        score = new Score(myGame, screenHeight);

        stoneArray = new Array<>();
        for (int i = 0; i < COUNT_STONE; i++) {
            stoneArray.add(new Stone(myGame, i + 1, COUNT_STONE, stoneArray, screenWidth, SPEED, score));
        }

        flowerArray = new Array<>();
        for (int i = 0; i < COUNT_FLOWER; i++) {
            flowerArray.add(new Flower(myGame, i + 1, COUNT_STONE, flowerArray, screenWidth, SPEED));
        }

        pausePlayBtn = new Button(myGame.getAssets().getSkin(), "gamePauseBtn");
        pausePlayBtn.setPosition(screenWidth - pausePlayBtn.getMinWidth(),
                screenHeight - pausePlayBtn.getMinHeight());

        pauseScreen = new PauseScreen(myGame, GameScreen.this);

        stage.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if (stage.hit(x, y, false) != pausePlayBtn) {
                    squirrel.onClick();
                }

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        pausePlayBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                myGame.setScreen(pauseScreen);

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        stage.addActor(new Forest(myGame));

        stage.addActor(squirrel);
        stage.addActor(ground);
        stage.addActor(score);
        for (int i = 0; i < stoneArray.size; i++) {
            stage.addActor(stoneArray.get(i));
        }

        for (int i = 0; i < flowerArray.size; i++) {
            stage.addActor(flowerArray.get(i));
        }

        stage.addActor(pausePlayBtn);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        for (Stone stone : stoneArray) {
            if (stone.getX() < 75) {
                if (squirrel.collides(stone.getBoundStone())) {
                    myGame.setScreen(new GameOverScreen(myGame, stage));
                    score.checkScore();
                }
            }
        }
    }

    @Override
    public void show() {
        super.show();
        squirrel.isAnimation(true);
    }

    @Override
    public void hide() {
        super.hide();
        squirrel.isAnimation(false);
    }
}
