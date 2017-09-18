package com.marcos.angel.redbatizfirebase.view;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcos.angel.redbatizfirebase.R;
import com.squareup.picasso.Picasso;

public class PictureDetailActivity extends AppCompatActivity {


    private ImageView imageHeader;
    private TextView txtUuserName, txtTitle, txtDescription, txtLikes;

    String image, username, title, description, likes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);

        imageHeader = (ImageView) findViewById(R.id.imageHeader);
        txtUuserName = (TextView) findViewById(R.id.userNameDetail);
        txtTitle = (TextView) findViewById(R.id.titleImage);
        txtDescription = (TextView) findViewById(R.id.textContentImageDetail);
        txtLikes = (TextView) findViewById(R.id.likeNumberDetail);

        Picasso.with(this).load(getIntent().getStringExtra("IMAGE")).into(imageHeader);
        txtUuserName.setText(getIntent().getStringExtra("USERNAME"));
        txtTitle.setText(getIntent().getStringExtra("TITLE"));
        txtDescription.setText(getIntent().getStringExtra("DESCRIPTION"));
        txtLikes.setText(getIntent().getStringExtra("LIKES"));


        showToolbar("",true);
        getWindow().setExitTransition(new Fade());
    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
    }
}
