package com.ifansdev.flyingsquirrel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.ifansdev.flyingsquirrel.MyGame;

public class BaseScreen implements Screen {
    MyGame myGame;
    Stage stage;

    int screenWidth;
    int screenHeight;

    public BaseScreen(MyGame myGame) {
        this.myGame = myGame;

        screenWidth = 320;
        screenHeight = (int) ( (float) Gdx.graphics.getHeight() / ( (float) Gdx.graphics.getWidth() / screenWidth));

        stage = new Stage(new FillViewport(screenWidth, screenHeight));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
