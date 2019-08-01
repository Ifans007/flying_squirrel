package com.ifansdev.flyingsquirrel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ifansdev.flyingsquirrel.screens.MenuScreen;

public class MyGame extends Game {
	private MenuScreen menuScreen;
	
	@Override
	public void create () {
	setScreen(new PreviewScreen(this));
	}

	void setScreen() {
		setScreen(menuScreen = new MenuScreen(this));
	}
	
	@Override
	public void dispose () {

	}

}
