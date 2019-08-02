package com.ifansdev.flyingsquirrel.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.ifansdev.flyingsquirrel.MyGame;
import com.ifansdev.flyingsquirrel.actors.Forest;
import com.ifansdev.flyingsquirrel.actors.Squirrel;
import com.ifansdev.flyingsquirrel.actors.Stone;
import com.ifansdev.flyingsquirrel.assets.Assets;


public class GameScreen extends BaseScreen {
    private Squirrel squirrel;

    private Array<Stone> stoneArray;
    private final int COUNT_STONE = 4;

    public GameScreen(MyGame myGame) {
        super(myGame);

        squirrel = new Squirrel(myGame);
        stoneArray = new Array<>();

        for (int i = 0; i < COUNT_STONE; i++) {
            stoneArray.add(new Stone(myGame, i + 1, COUNT_STONE, stoneArray, screenWidth));
        }

        Image ground = new Image(myGame.getAssets().getTexture(Assets.GROUND));

        stage.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                squirrel.onClick();
                
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        stage.addActor(new Forest(myGame));

        stage.addActor(squirrel);
        stage.addActor(ground);
        for (int i = 0; i < stoneArray.size; i++) {
            stage.addActor(stoneArray.get(i));
        }

    }
}
