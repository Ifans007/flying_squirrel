package com.ifansdev.flyingsquirrel;

import android.os.Bundle;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.ifansdev.flyingsquirrel.helpers.IForAndroidLauncher;

public class AndroidLauncher extends AndroidApplication implements IForAndroidLauncher{

	private Toast toast;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGame(this), config);
	}

	@Override
	public void showToast(final String textToast) {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				toast.cancel();
				toast = Toast.makeText(getApplicationContext(), textToast, Toast.LENGTH_SHORT);
				toast.show();
			}
		});

	}
}
