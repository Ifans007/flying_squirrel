package com.ifansdev.flyingsquirrel.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ifansdev.flyingsquirrel.MyGame;

public class GameOverScreen extends BaseScreen {
    private Stage stageGameScreen;
    public GameOverScreen(final MyGame myGame, Stage stageGameScreen) {
        super(myGame);
        this.stageGameScreen = stageGameScreen;

        stage.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                myGame.setScreen(new GameScreen(myGame));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        stageGameScreen.draw();
    }
}
