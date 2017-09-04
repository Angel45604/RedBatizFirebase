package com.marcos.angel.redbatizfirebase.post.view;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.crash.FirebaseCrash;
import com.marcos.angel.redbatizfirebase.R;
import com.marcos.angel.redbatizfirebase.adapter.PictureAdapterRecyclerView;
import com.marcos.angel.redbatizfirebase.model.Picture;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final int REQUEST_CAMERA = 1;
    private FloatingActionButton fabCamera;
    private String photoPathTemp = "";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.tab_home), false, view);
        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureRecycler);
        fabCamera = (FloatingActionButton) view.findViewById(R.id.fabCamera);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture, getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        return view;
    }

    private void takePicture() {
        Intent intentTakePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentTakePicture.resolveActivity(getActivity().getPackageManager())!=null){
            File photoFile = null;

            try{
                photoFile=createImageFile();
            }catch(Exception e){
                e.printStackTrace();
                FirebaseCrash.report(e);
            }
            if(photoFile!=null){
                Uri photoUri= FileProvider.getUriForFile(getActivity(),"com.marcos.angel.redbatizfirebase",photoFile);
                intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(intentTakePicture,REQUEST_CAMERA);
            }

        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(new Date());
        String imageFileName = "JPEG_"+timeStamp+"_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File photo = File.createTempFile(imageFileName,".jpg",storageDir);

        photoPathTemp = "file:"+photo.getAbsolutePath();

        return photo;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CAMERA&&resultCode==getActivity().RESULT_OK){
            Log.d("Home Fragment", "CAMERA OK");
            Intent i = new Intent(getActivity(),NewPostActivity.class);
            i.putExtra("PHOTO_PATH_TEMP",photoPathTemp);
            startActivity(i);
        }
    }

    public ArrayList<Picture> buildPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("http://www.novalandtours.com/images/guide/guilin.jpg", "Angel Marcos", "4 Dias", "3"));
        pictures.add(new Picture("https://www.google.com.mx/imgres?imgurl=https%3A%2F%2Fthumb1.shutterstock.com%2Fdisplay_pic_with_logo%2F3910382%2F616462838%2Fstock-photo-sample-stamp-on-white-background-sample-stamp-sign-616462838.jpg&imgrefurl=https%3A%2F%2Fwww.shutterstock.com%2Fes%2Fsearch%2Fsample&docid=rcle1eVejgiZSM&tbnid=YFNWPACA-PXPGM%3A&vet=10ahUKEwjl55ut5_XVAhUBdiYKHZoVCCwQMwgtKAcwBw..i&w=450&h=331&bih=638&biw=1360&q=sample%20image&ved=0ahUKEwjl55ut5_XVAhUBdiYKHZoVCCwQMwgtKAcwBw&iact=mrc&uact=8", "Pablo Ramon", "3 Dias", "10"));
        pictures.add(new Picture("https://www.google.com.mx/imgres?imgurl=https%3A%2F%2Fthumb1.shutterstock.com%2Fdisplay_pic_with_logo%2F3910382%2F616462838%2Fstock-photo-sample-stamp-on-white-background-sample-stamp-sign-616462838.jpg&imgrefurl=https%3A%2F%2Fwww.shutterstock.com%2Fes%2Fsearch%2Fsample&docid=rcle1eVejgiZSM&tbnid=YFNWPACA-PXPGM%3A&vet=10ahUKEwjl55ut5_XVAhUBdiYKHZoVCCwQMwgtKAcwBw..i&w=450&h=331&bih=638&biw=1360&q=sample%20image&ved=0ahUKEwjl55ut5_XVAhUBdiYKHZoVCCwQMwgtKAcwBw&iact=mrc&uact=8", "No se Quien", "2 Dias", "1"));
        return pictures;

    }

    public void showToolbar(String title, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
