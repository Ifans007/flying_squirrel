package com.ifansdev.flyingsquirrel.helpers;

public interface IForAndroidLauncher {
    IAdMob getAdMobShow();

    IMySignInClient getMySignInClient();

    com.ifansdev.flyingsquirrel.helpers.IMyLeaderboard getMyLeaderboard();

    void showToast(String textToast);

}

