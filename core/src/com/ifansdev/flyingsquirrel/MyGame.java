package com.ifansdev.flyingsquirrel;

import com.badlogic.gdx.Game;
import com.ifansdev.flyingsquirrel.assets.Assets;
import com.ifansdev.flyingsquirrel.assets.SaveScore;
import com.ifansdev.flyingsquirrel.helpers.IForAndroidLauncher;
import com.ifansdev.flyingsquirrel.helpers.SendRequestInternet;
import com.ifansdev.flyingsquirrel.screens.MenuScreen;

public class MyGame extends Game {
	private IForAndroidLauncher iForAndroidLauncher;
	private SendRequestInternet sendRequestInternet;
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
		sendRequestInternet  = new SendRequestInternet(this);
		assets               = new Assets();
		saveScore            = new SaveScore();
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

	public SendRequestInternet getSendRequestInternet() {
		return sendRequestInternet;
	}

	public IForAndroidLauncher getIForAndroidLauncher() {
		return iForAndroidLauncher;
	}

	void isConnect(boolean b) {
		menuScreen.isConnect(b);
	}

	@Override
	public void dispose () {
		assets.dispose();

	}

}
