package com.marcos.angel.redbatizfirebase.view.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marcos.angel.redbatizfirebase.R;
import com.marcos.angel.redbatizfirebase.adapter.PictureAdapterRecyclerView;
import com.marcos.angel.redbatizfirebase.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView usernameProfile;
    private String image;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        showToolbar("",false,view);

        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureProfileRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture, getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);
        return view;
    }

    public ArrayList<Picture> buildPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://www.google.com.mx/imgres?imgurl=https%3A%2F%2Fthumb1.shutterstock.com%2Fdisplay_pic_with_logo%2F3910382%2F616462838%2Fstock-photo-sample-stamp-on-white-background-sample-stamp-sign-616462838.jpg&imgrefurl=https%3A%2F%2Fwww.shutterstock.com%2Fes%2Fsearch%2Fsample&docid=rcle1eVejgiZSM&tbnid=YFNWPACA-PXPGM%3A&vet=10ahUKEwjl55ut5_XVAhUBdiYKHZoVCCwQMwgtKAcwBw..i&w=450&h=331&bih=638&biw=1360&q=sample%20image&ved=0ahUKEwjl55ut5_XVAhUBdiYKHZoVCCwQMwgtKAcwBw&iact=mrc&uact=8", "Angel Marcos", "4 Dias", "3"));
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
