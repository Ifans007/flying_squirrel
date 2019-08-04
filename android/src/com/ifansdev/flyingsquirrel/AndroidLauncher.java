package com.ifansdev.flyingsquirrel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.MobileAds;
import com.ifansdev.flyingsquirrel.helpers.AdMob;
import com.ifansdev.flyingsquirrel.helpers.IForAndroidLauncher;
import com.ifansdev.flyingsquirrel.helpers.MyLeaderboard;
import com.ifansdev.flyingsquirrel.helpers.MySignInClient;

public class AndroidLauncher extends AndroidApplication implements IForAndroidLauncher{

	public MyGame myGame;

	private AdMob          adMob;
	public  MySignInClient mySignInClient;
	public  MyLeaderboard  myLeaderboard;

	private Toast toast;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		RelativeLayout layout = new RelativeLayout(this);
		layout.addView(initializeForView(myGame = new MyGame(this), config));

		adMob = new AdMob(this);
		layout.addView(adMob.getAdView());
		setContentView(layout);

		toast = new Toast(this);

		myLeaderboard = new MyLeaderboard(this, myGame);
		mySignInClient = new MySignInClient(this, myLeaderboard);
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 9001) {
			if (resultCode == RESULT_OK) {
				myGame.isConnect(true);
			} else {
				myGame.isConnect(false);
			}
		}
	}

	@Override
	public AdMob getAdMobShow() {
		return adMob;
	}

	@Override
	public MySignInClient getMySignInClient() {
		return mySignInClient;
	}

	@Override
	public MyLeaderboard getMyLeaderboard() {
		return myLeaderboard;
	}

	@Override
	protected void onStart() {
		super.onStart();
		mySignInClient.signInSilently();
	}

	@Override
	protected void onPause() {
		toast.cancel();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mySignInClient.signInSilently();
		super.onResume();
	}

}
