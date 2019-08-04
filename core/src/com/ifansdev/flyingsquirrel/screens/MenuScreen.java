package com.ifansdev.flyingsquirrel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ifansdev.flyingsquirrel.MyGame;
import com.ifansdev.flyingsquirrel.actors.Forest;
import com.ifansdev.flyingsquirrel.helpers.SendRequestInternet;

public class MenuScreen extends BaseScreen {

    private Label highScore;

    private Button menuStartBtn;
    private Button googlePlayGamesBtn;
    private Button leaderboardsBtn;

    private long backPressed;
    private Label exitLabel;

    public MenuScreen(final MyGame myGame) {
        super(myGame);

        stage.addActor(new Forest(myGame));

        highScore = new Label(
                "High Score " + myGame.getSaveScore().getHighScore(),
                myGame.getAssets().getSkin());
        highScore.setPosition(10, screenHeight - 22);

        stage.addActor(highScore);

        createBtn(myGame.getAssets().getSkin());
        checkSignInClient();

        menuStartBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                myGame.setScreen(new GameScreen(myGame));
                super.clicked(event, x, y);
            }
        });

        googlePlayGamesBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickGooglePlayGames();
                super.clicked(event, x, y);
            }
        });

        leaderboardsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (myGame.getIForAndroidLauncher() != null) {
                    myGame.getSendRequestInternet().SendRequest(SendRequestInternet.SHOW_LEADERBOARDS);
                }

                super.clicked(event, x, y);
            }
        });

        exitLabel.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Gdx.app.exit();

                return super.touchDown(event, x, y, pointer, button);
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
        googlePlayGamesBtn = new Button(table.getSkin(), "googlePlayGamesBtn");
        leaderboardsBtn = new Button(table.getSkin(), "leaderboardsBtn");
        exitLabel = new Label("EXIT", myGame.getAssets().getSkin());
        exitLabel.setPosition(240, 40);

        table.row().expandX();
        table.add(googlePlayGamesBtn).uniformX();
        table.add(menuStartBtn).uniformX();
        table.add(leaderboardsBtn).uniformX();

        stage.addActor(table);
        stage.addActor(exitLabel);
    }

    private void clickGooglePlayGames() {
        if (myGame.getIForAndroidLauncher() == null) {
            return;
        }

        if (myGame.getIForAndroidLauncher().getMySignInClient().isSignedIn()) {
            myGame.getIForAndroidLauncher().getMySignInClient().signOut();
            googlePlayGamesSetChecked(false);

        } else {
            myGame.getSendRequestInternet().SendRequest(SendRequestInternet.CHECK_GOOGLE_PLAY_GAMES_BTN);
        }
    }

    public void isConnect(boolean b) {
        googlePlayGamesSetChecked(b);
    }

    private void checkSignInClient() {
        if (myGame.getIForAndroidLauncher() == null) {
            return;
        }

        if (myGame.getIForAndroidLauncher().getMySignInClient().isSignedIn()) {
            googlePlayGamesSetChecked(true);

        } else {
            googlePlayGamesSetChecked(false);
        }
    }

    private void googlePlayGamesSetChecked(boolean b) {
        googlePlayGamesBtn.setChecked(b);
    }

    @Override
    public void show() {
        super.show();

        if (myGame.getIForAndroidLauncher() != null) {
            myGame.getIForAndroidLauncher().getAdMobShow().showAds(true);
        }
        highScore.setText("High Score " + myGame.getSaveScore().getHighScore());
    }

    @Override
    public void hide() {
        super.hide();
        if (myGame.getIForAndroidLauncher() != null) {
            myGame.getIForAndroidLauncher().getAdMobShow().showAds(false);
        }
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
