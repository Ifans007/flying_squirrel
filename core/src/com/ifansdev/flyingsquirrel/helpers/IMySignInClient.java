package com.ifansdev.flyingsquirrel.helpers;

public interface IMySignInClient {
    boolean isSignedIn();

    void signIn();        //нажатие кнопки входа в Google Play

    void signOut();       //нажатие кнопки выхода из Google Play
}
