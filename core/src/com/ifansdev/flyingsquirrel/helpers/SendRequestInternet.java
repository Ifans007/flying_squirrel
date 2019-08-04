package com.ifansdev.flyingsquirrel.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;

public class SendRequestInternet {
    private com.ifansdev.flyingsquirrel.MyGame myGame;
    private Net.HttpRequest                    request;

    public static final int CHECK_GOOGLE_PLAY_GAMES_BTN = 0;
    public static final int SHOW_LEADERBOARDS           = 1;


    public SendRequestInternet(com.ifansdev.flyingsquirrel.MyGame myGame) {
        this.myGame = myGame;

        request = new Net.HttpRequest(Net.HttpMethods.GET);
        request.setUrl("https://www.google.com");
    }

    public void SendRequest(final int check) {
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {

                switch (check) {
                    case CHECK_GOOGLE_PLAY_GAMES_BTN:
                        myGame.getIForAndroidLauncher().getMySignInClient().signIn();
                        break;
                    case SHOW_LEADERBOARDS:
                        if (myGame.getIForAndroidLauncher().getMySignInClient().isSignedIn()) {
                            myGame.getIForAndroidLauncher().getMyLeaderboard().onShowLeaderboardsRequsted();
                        } else {
                            myGame.getIForAndroidLauncher().showToast("Sign in Google Play Games");
                        }
                        break;
                }
            }

            @Override
            public void failed(Throwable t) {

                if (check == CHECK_GOOGLE_PLAY_GAMES_BTN) {
                    myGame.getMenuScreen().isConnect(false);
                }

                myGame.getIForAndroidLauncher().showToast("Network error. " +
                        "Check your network connections and try again");


            }

            @Override
            public void cancelled() {

            }
        });
    }
}
