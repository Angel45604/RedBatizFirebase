package com.marcos.angel.redbatizfirebase.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcos.angel.redbatizfirebase.R;
import com.marcos.angel.redbatizfirebase.adapter.PictureAdapterRecyclerView;
import com.marcos.angel.redbatizfirebase.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


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
        pictures.add(new Picture("https://www.google.com.mx/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwih6JCFp-bVAhXOZiYKHYZVDCoQjRwIBw&url=https%3A%2F%2Fwww.flooringvillage.co.uk%2Frequest-a-sample-547-p.asp&psig=AFQjCNEl7ieyy5FrncHXQ-N5E3TlHlEwjg&ust=1503335421649267", "Angel Marcos", "4 Dias", "3"));
        pictures.add(new Picture("https://www.google.com.mx/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwih6JCFp-bVAhXOZiYKHYZVDCoQjRwIBw&url=https%3A%2F%2Fwww.flooringvillage.co.uk%2Frequest-a-sample-547-p.asp&psig=AFQjCNEl7ieyy5FrncHXQ-N5E3TlHlEwjg&ust=1503335421649267", "Pablo Ramon", "3 Dias", "10"));
        pictures.add(new Picture("https://www.google.com.mx/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwih6JCFp-bVAhXOZiYKHYZVDCoQjRwIBw&url=https%3A%2F%2Fwww.flooringvillage.co.uk%2Frequest-a-sample-547-p.asp&psig=AFQjCNEl7ieyy5FrncHXQ-N5E3TlHlEwjg&ust=1503335421649267", "No se Quien", "2 Dias", "1"));
        return pictures;

    }

    public void showToolbar(String title, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
