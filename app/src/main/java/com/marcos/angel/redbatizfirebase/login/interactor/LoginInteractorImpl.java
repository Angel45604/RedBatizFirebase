package com.marcos.angel.redbatizfirebase.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.marcos.angel.redbatizfirebase.login.presenter.LoginPresenter;
import com.marcos.angel.redbatizfirebase.login.repository.LoginRepository;
import com.marcos.angel.redbatizfirebase.login.repository.LoginRepositoryImpl;

/**
 * Created by angel on 27/08/2017.
 */

public class LoginInteractorImpl implements LoginInteractor{

    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
        repository= new LoginRepositoryImpl(presenter);
    }

    @Override
    public void singIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {
        repository.signIn(username,password, activity, firebaseAuth);
    }
}
