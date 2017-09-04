package com.marcos.angel.redbatizfirebase.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.marcos.angel.redbatizfirebase.R;
import com.marcos.angel.redbatizfirebase.login.view.LoginActivity;
import com.marcos.angel.redbatizfirebase.post.view.HomeFragment;
import com.marcos.angel.redbatizfirebase.view.fragment.ProfileFragment;
import com.marcos.angel.redbatizfirebase.view.fragment.SearchFragment;

public class ContainerActivity extends AppCompatActivity {
    private static final String TAG = "ContainerActivity";
    private  BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private ProfileFragment profileFragment;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseInitialize();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        profileFragment = new ProfileFragment();


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNV);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_bottombar) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(null).commit();
                } else if (item.getItemId() == R.id.search_bottombar) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(null).commit();
                } else if (item.getItemId() == R.id.me_bottombar) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(null).commit();
                }
                return true;
            }
        });

    }

    private void firebaseInitialize(){
        firebaseAuth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    Log.w(TAG,"User logged in "+ firebaseUser.getEmail());
                }else{
                    Log.w(TAG, "Usuario NO loggeado");
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch(item.getItemId()){
            case R.id.mSignOut:
                firebaseAuth.signOut();
                if(LoginManager.getInstance()!=null){
                    LoginManager.getInstance().logOut();
                }
                Toast.makeText(this, "Sesión cerrada exitosamente", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(ContainerActivity.this, LoginActivity.class);
                startActivity(i);
                break;
            
            case R.id.mAbout:
                Toast.makeText(this, "ESE SOY YO", Toast.LENGTH_SHORT).show();
                break;
        }
        
        return super.onOptionsItemSelected(item);
    }
}
