package com.ifansdev.flyingsquirrel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ifansdev.flyingsquirrel.MyGame;
import com.ifansdev.flyingsquirrel.actors.Forest;

public class MenuScreen extends BaseScreen {

    private Button menuStartBtn;

    private long backPressed;

    public MenuScreen(final MyGame myGame) {
        super(myGame);

        stage.addActor(new Forest(myGame));

        createBtn(myGame.getAssets().getSkin());

        menuStartBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                myGame.setScreen(new GameScreen(myGame));
                super.clicked(event, x, y);
            }
        });

        stage.addListener(new ClickListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.BACK) {
                    closeApp();

                } else {

                    if (keycode == Input.Keys.BACKSPACE) {
                        Gdx.app.exit();
                    }
                }
                return super.keyUp(event, keycode);
            }
        });
    }

    private void createBtn(Skin skin) {
        Table table = new Table(skin);

        table.setFillParent(true);

        menuStartBtn = new Button(table.getSkin(), "menuPlayBtn");

        table.row().expandX();
        table.add(menuStartBtn).uniformX();

        stage.addActor(table);
    }

    private void closeApp() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            Gdx.app.exit();
        } else {
            myGame.getIForAndroidLauncher().showToast("Press again to exit");
        }

        backPressed = System.currentTimeMillis();
    }
}
