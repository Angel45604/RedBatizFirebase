package com.marcos.angel.redbatizfirebase.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.marcos.angel.redbatizfirebase.login.interactor.LoginInteractor;
import com.marcos.angel.redbatizfirebase.login.interactor.LoginInteractorImpl;
import com.marcos.angel.redbatizfirebase.login.view.LoginView;

/**
 * Created by angel on 27/08/2017.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void singIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth)
     {
        loginView.disableInputs();
        loginView.showProgressVar();
        interactor.singIn(username,password, activity, firebaseAuth);
    }

    @Override
    public void loginSuccess() {
        loginView.goHome();
        loginView.hideProgressBar();
    }

    @Override
    public void loginError(String error) {
        loginView.enableInputs();
        loginView.hideProgressBar();
        loginView.loginError(error);
    }
}
