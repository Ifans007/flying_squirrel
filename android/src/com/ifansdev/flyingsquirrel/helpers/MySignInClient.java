package com.ifansdev.flyingsquirrel.helpers;

import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.ifansdev.flyingsquirrel.AndroidLauncher;

public class MySignInClient implements IMySignInClient {
    private GoogleSignInClient mGoogleSignInClient;
    private MyLeaderboard myLeaderboard;

    private static final int RC_SIGN_IN = 9001;
    private AndroidLauncher androidLauncher;

    public MySignInClient(AndroidLauncher androidLauncher, MyLeaderboard myLeaderboard) {
        this.androidLauncher = androidLauncher;
        this.myLeaderboard   = myLeaderboard;

        mGoogleSignInClient = GoogleSignIn.getClient(androidLauncher, GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
    }

    @Override
    public void signIn() {
        androidLauncher.startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
    }

    @Override
    public void signOut() {
        if (!isSignedIn()) {
            return;
        }

        mGoogleSignInClient.signOut().addOnCompleteListener(androidLauncher,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        myLeaderboard.onDisconnected();
                    }
                });
    }

    @Override
    public boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(androidLauncher) != null;
    }

    public void signInSilently() {

        mGoogleSignInClient.silentSignIn().addOnCompleteListener(androidLauncher,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if(task.isSuccessful()) {
                            myLeaderboard.onConnected(task.getResult());
                        } else {
                            myLeaderboard.onDisconnected();
                        }
                    }
                }
        );
    }
}
