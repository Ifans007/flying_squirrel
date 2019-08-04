package com.ifansdev.flyingsquirrel.helpers;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.ifansdev.flyingsquirrel.AndroidLauncher;
import com.ifansdev.flyingsquirrel.MyGame;
import com.ifansdev.flyingsquirrel.R;

public class MyLeaderboard  implements IMyLeaderboard {
    private AndroidLauncher androidLauncher;
    private MyGame myGame;

    private LeaderboardsClient mLeadearboardsClient;

    private final int RC_LEADERBOARD_UI = 9004;

    public MyLeaderboard(AndroidLauncher androidLauncher, MyGame myGame) {
        this.androidLauncher = androidLauncher;
        this.myGame          = myGame;
    }

    void onConnected(GoogleSignInAccount googleSignInAccount) {
        mLeadearboardsClient = Games.getLeaderboardsClient(androidLauncher, googleSignInAccount);
    }

    void onDisconnected() {
        mLeadearboardsClient = null;
    }

    public void onShowLeaderboardsRequsted() {
        if (mLeadearboardsClient == null) {
            return;
        }

        mLeadearboardsClient.getAllLeaderboardsIntent()
                .addOnSuccessListener(new OnSuccessListener<Intent>() {
                    @Override
                    public void onSuccess(Intent intent) {
                        androidLauncher.startActivityForResult(intent, RC_LEADERBOARD_UI);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void pushAccomplishments(int countScore) {
        if (mLeadearboardsClient == null) {
            return;
        }

        mLeadearboardsClient.submitScore(
                androidLauncher.getString(R.string.leaderboard),
                countScore);
    }
}
