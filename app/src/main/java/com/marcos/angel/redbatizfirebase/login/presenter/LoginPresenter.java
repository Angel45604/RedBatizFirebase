package com.marcos.angel.redbatizfirebase.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by angel on 27/08/2017.
 */

public interface LoginPresenter {
    void singIn(String username, String password, Activity activity,FirebaseAuth firebaseAuth);
    void loginSuccess();
    void loginError(String error);
}
