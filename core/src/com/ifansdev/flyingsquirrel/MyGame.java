package com.ifansdev.flyingsquirrel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ifansdev.flyingsquirrel.actors.Score;
import com.ifansdev.flyingsquirrel.assets.Assets;
import com.ifansdev.flyingsquirrel.assets.SaveScore;
import com.ifansdev.flyingsquirrel.helpers.IForAndroidLauncher;
import com.ifansdev.flyingsquirrel.screens.BaseScreen;
import com.ifansdev.flyingsquirrel.screens.GameScreen;
import com.ifansdev.flyingsquirrel.screens.MenuScreen;

public class MyGame extends Game {
	private IForAndroidLauncher iForAndroidLauncher;
	private MenuScreen menuScreen;
	private Assets assets;
	private SaveScore saveScore;

	@Override
	public void create () {
	setScreen(new PreviewScreen(this));
	}

	MyGame(IForAndroidLauncher iForAndroidLauncher) {
		this.iForAndroidLauncher = iForAndroidLauncher;
	}

	public MyGame() {

	}

	void setScreen() {
		assets     = new Assets();
		saveScore  = new SaveScore();
		setScreen(menuScreen = new MenuScreen(this));
	}

	public MenuScreen getMenuScreen() {
		return menuScreen;
	}

	public Assets getAssets() {
		return assets;
	}

	public SaveScore getSaveScore() {
		return saveScore;
	}

	public IForAndroidLauncher getIForAndroidLauncher() {
		return iForAndroidLauncher;
	}

	@Override
	public void dispose () {
		assets.dispose();

	}

}
