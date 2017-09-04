package com.marcos.angel.redbatizfirebase.login.view;

/**
 * Created by angel on 27/08/2017.
 */

public interface LoginView {
    void enableInputs();
    void disableInputs();

    void showProgressVar();
    void hideProgressBar();

    void loginError(String error);

    void goCreateAccount();
    void goHome();
}
