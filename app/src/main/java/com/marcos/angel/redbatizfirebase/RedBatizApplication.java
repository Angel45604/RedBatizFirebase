package com.marcos.angel.redbatizfirebase;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by angel on 29/08/2017.
 */

public class RedBatizApplication extends Application {

    private static final String TAG = "RedBatizApplication";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseStorage firebaseStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseCrash.log("Initializing RedBatiz");

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    FirebaseCrash.logcat(Log.WARN,TAG,"User Logged In "+firebaseUser.getEmail());
                }else{
                    FirebaseCrash.logcat(Log.WARN,TAG,"User NO Logged In");
                }
            }
        };

        firebaseStorage = FirebaseStorage.getInstance();

    }

    public StorageReference getStorageReference(){
        return firebaseStorage.getReference();
    }
}
