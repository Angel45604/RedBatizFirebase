package com.marcos.angel.redbatizfirebase.login.repository;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by angel on 27/08/2017.
 */

public interface LoginRepository {
    void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth);

}
