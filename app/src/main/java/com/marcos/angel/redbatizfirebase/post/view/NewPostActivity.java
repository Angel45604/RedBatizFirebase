package com.marcos.angel.redbatizfirebase.post.view;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.marcos.angel.redbatizfirebase.R;
import com.marcos.angel.redbatizfirebase.RedBatizApplication;
import com.marcos.angel.redbatizfirebase.model.Picture;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewPostActivity extends AppCompatActivity {

    private static final String TAG = "NewPostActivity";
    private static final String POSTS_NODE = "Posts";
    private ImageView imgPhoto;
    private Button btnCreatePost;
    private TextInputEditText txtTitle, txtDescription;

    private String photoPath;
    private RedBatizApplication app;
    private StorageReference storageReference;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        app = (RedBatizApplication) getApplicationContext();
        storageReference = app.getStorageReference();

        imgPhoto = (ImageView) findViewById(R.id.imgPhoto);
        btnCreatePost = (Button) findViewById(R.id.btnCreatePost);
        txtTitle = (TextInputEditText) findViewById(R.id.edtTitle);
        txtDescription = (TextInputEditText) findViewById(R.id.edtDescription);
        if(getIntent().getExtras()!=null){
            photoPath = getIntent().getExtras().getString("PHOTO_PATH_TEMP");
            showPhoto();
        }

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPhoto();
            }
        });

    }

    public void createPost(){

    }

    private void uploadPhoto() {
        Picture picture = new Picture();
        final String timeStamp = new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(new Date());

        imgPhoto.setDrawingCacheEnabled(true);
        imgPhoto.buildDrawingCache();

        Bitmap bitmap = imgPhoto.getDrawingCache();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] photoByte= byteArrayOutputStream.toByteArray();

        String photoName =  photoPath.substring(photoPath.lastIndexOf("/")+1,photoPath.length());

        StorageReference photoReference =storageReference.child("postImages/"+photoName);

        UploadTask uploadTask = photoReference.putBytes(photoByte);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error al subir la foto " + e.toString());
                e.printStackTrace();
                FirebaseCrash.report(e);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri uriPhoto = taskSnapshot.getDownloadUrl();
                String photoURL = uriPhoto.toString();
                Log.w(TAG, "URL Photo: " +photoURL);

                //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                databaseReference = FirebaseDatabase.getInstance().getReference();
                Picture picture = new Picture(databaseReference.push().getKey(), photoURL,"Angel", "10", timeStamp, txtTitle.getText().toString(), txtDescription.getText().toString());
                Log.d(TAG,"PICTURE "+picture);
                databaseReference.child(POSTS_NODE).child(picture.getId()).setValue(picture);
                finish();
            }
        });



    }

    private void showPhoto(){
        Picasso.with(this).load(photoPath).into(imgPhoto);
    }
}
