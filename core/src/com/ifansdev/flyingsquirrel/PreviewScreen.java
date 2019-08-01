package com.ifansdev.flyingsquirrel;

import com.badlogic.gdx.Screen;

class PreviewScreen implements Screen {
    private MyGame myGame;

    public PreviewScreen(MyGame myGame) {
        this.myGame = myGame;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        if (width > height) {
            myGame.setScreen();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
