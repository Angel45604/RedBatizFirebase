package com.marcos.angel.redbatizfirebase.login.view;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.marcos.angel.redbatizfirebase.R;

public class CreateAccountActivity extends AppCompatActivity {

    public static final String TAG ="CreateAccountActivity" ;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private Button btnJoinUs;
    private TextInputEditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.toolbar_title_create_account), true);

        btnJoinUs = (Button) findViewById(R.id.joinUs);
        edtEmail = (TextInputEditText) findViewById(R.id.email);
        edtPassword = (TextInputEditText) findViewById(R.id.password_create_account);


        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    Log.w(TAG,"User logged In "+firebaseUser.getEmail());
                }else{
                    Log.w(TAG,"Usuario no loggeado");
                }
            }
        };

        btnJoinUs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void createAccount() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CreateAccountActivity.this, "Cuenta Creada Exitosamente", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(CreateAccountActivity.this, "Ocurrió un Error al crear la cuenta", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
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
}
