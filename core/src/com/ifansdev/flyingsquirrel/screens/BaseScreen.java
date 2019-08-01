package com.ifansdev.flyingsquirrel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.ifansdev.flyingsquirrel.MyGame;

public class BaseScreen implements Screen {
    MyGame myGame;
    Stage stage;

    private int gameWidth;
    private int gameHeight;

    public BaseScreen(MyGame myGame) {
        this.myGame = myGame;

        gameWidth = 320;
        gameHeight = (int) ( (float) Gdx.graphics.getHeight() / ( (float) Gdx.graphics.getWidth() / gameWidth));

        stage = new Stage(new FillViewport(gameWidth, gameHeight));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(148/255f,204/255f,1,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
