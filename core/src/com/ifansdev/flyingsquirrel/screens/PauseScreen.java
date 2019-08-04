package com.ifansdev.flyingsquirrel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ifansdev.flyingsquirrel.MyGame;

public class PauseScreen extends BaseScreen {
    private GameScreen gameScreen;

    private Button gamePlayBtn;

    private Label menuLabel;
    private Label exitLabel;
    public PauseScreen(final MyGame myGame, final GameScreen gameScreen) {
        super(myGame);
        this.gameScreen = gameScreen;

        gamePlayBtn = new Button(myGame.getAssets().getSkin(), "gamePlayBtn");
        gamePlayBtn.setPosition(screenWidth - gamePlayBtn.getMinWidth(),
                screenHeight - gamePlayBtn.getMinHeight());

        menuLabel = new Label("MENU", myGame.getAssets().getSkin());
        menuLabel.setPosition(240, 100);

        exitLabel = new Label("EXIT", myGame.getAssets().getSkin());
        exitLabel.setPosition(240, 40);

        gamePlayBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                myGame.setScreen(gameScreen);

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        menuLabel.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                myGame.setScreen(myGame.getMenuScreen());

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        exitLabel.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Gdx.app.exit();

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        stage.addActor(gamePlayBtn);
        stage.addActor(menuLabel);
        stage.addActor(exitLabel);
    }

    @Override
    public void render(float delta) {
        gameScreen.stage.draw();

        super.render(delta);

    }
}
