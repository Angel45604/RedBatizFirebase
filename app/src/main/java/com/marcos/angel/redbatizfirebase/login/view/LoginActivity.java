package com.marcos.angel.redbatizfirebase.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.marcos.angel.redbatizfirebase.R;
import com.marcos.angel.redbatizfirebase.login.presenter.LoginPresenter;
import com.marcos.angel.redbatizfirebase.login.presenter.LoginPresenterImpl;
import com.marcos.angel.redbatizfirebase.view.ContainerActivity;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private TextInputEditText username, password;
    private Button login;
    private LoginButton loginButtonFacebook;
    private ProgressBar progressBarLogin;
    private LoginPresenter presenter;

    public static final String TAG ="LoginActivity" ;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager=CallbackManager.Factory.create();

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    Log.w(TAG,"User logged In "+firebaseUser.getEmail());
                    goHome();
                }else{
                    Log.w(TAG,"Usuario no loggeado");
                }
            }
        };

        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        loginButtonFacebook =(LoginButton) findViewById(R.id.login_facebook);
        progressBarLogin = (ProgressBar) findViewById(R.id.progressBarLogin);
        hideProgressBar();

        presenter = new LoginPresenterImpl(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //if(username.equals(""))
                signIn(username.getText().toString(), password.getText().toString());
            }
        });

        loginButtonFacebook.setReadPermissions(Arrays.asList("email"));
        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.w(TAG, "Facebook Login Success Token: "+loginResult.getAccessToken().getToken());
                signInFacebookFirebase(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.w(TAG, "Facebook Login Cancelado ");
            }

            @Override
            public void onError(FacebookException e) {
                Log.w(TAG, "Facebook Login Error: "+e.toString());
                e.printStackTrace();
                FirebaseCrash.report(e);

            }
        });
    }

    private void signInFacebookFirebase(AccessToken accessToken) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());

        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = task.getResult().getUser();

                    SharedPreferences preferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email",user.getEmail());
                    editor.commit();
                    goHome();
                    FirebaseCrash.logcat(Log.WARN,TAG,"Login Facebook Success");
                    Toast.makeText(LoginActivity.this, "Login Facebook Exitoso", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseCrash.logcat(Log.WARN,TAG,"Login Facebook NO Success");
                    Toast.makeText(LoginActivity.this, "Login Facebook NO Exitoso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signIn(String username, String password) {
        presenter.singIn(username, password, this, firebaseAuth);
    }

    public void goCreateAccount(View view){
        goCreateAccount();
    }

    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        login.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        login.setEnabled(false);
    }

    @Override
    public void showProgressVar() {
        progressBarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarLogin.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this, getString(R.string.login_error)+error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void goHome() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
