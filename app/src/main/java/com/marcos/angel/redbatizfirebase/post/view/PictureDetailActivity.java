package com.marcos.angel.redbatizfirebase.post.view;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.StorageReference;
import com.marcos.angel.redbatizfirebase.R;
import com.marcos.angel.redbatizfirebase.RedBatizApplication;
import com.squareup.picasso.Picasso;

public class PictureDetailActivity extends AppCompatActivity {

    private static final String PHOTO_NAME = "";
    private ImageView imageHeader;
    private RedBatizApplication app;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);

        app = (RedBatizApplication) getApplicationContext();
        storageReference=app.getStorageReference();

        imageHeader = (ImageView)findViewById(R.id.imageHeader);

        showToolbar("",true);
        getWindow().setExitTransition(new Fade());
        showData();
    }

    private void showData(){
        storageReference.child("postImages/"+PHOTO_NAME)
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(PictureDetailActivity.this).load(uri.toString()).into(imageHeader);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PictureDetailActivity.this, "Ocurrió un error al solicitar la foto", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                FirebaseCrash.report(e);
            }
        });
    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
    }
}
