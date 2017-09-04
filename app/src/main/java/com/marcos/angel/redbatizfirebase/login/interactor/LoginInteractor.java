package com.marcos.angel.redbatizfirebase.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by angel on 27/08/2017.
 */

public interface LoginInteractor {
    void singIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth);
}
