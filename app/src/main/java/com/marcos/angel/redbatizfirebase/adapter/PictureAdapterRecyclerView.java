package com.marcos.angel.redbatizfirebase.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcos.angel.redbatizfirebase.R;
import com.marcos.angel.redbatizfirebase.model.Picture;
import com.marcos.angel.redbatizfirebase.view.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by angel on 20/08/2017.
 */

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder>{

    private ArrayList<Picture> pictures;
    private int resource;
    private Activity activity;

    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        final Picture picture = pictures.get(position);
        holder.userNameCard.setText(picture.getUserName());
        holder.timeCard.setText(picture.getTime());
        holder.likeNumberCard.setText(picture.getLikeNumber());
        Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCard);

        holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PictureDetailActivity.class);
                intent.putExtra("IMAGE", picture.getPicture());
                intent.putExtra("USERNAME", picture.getUserName());
                intent.putExtra("TITLE", picture.getTitle());
                intent.putExtra("DESCRIPTION", picture.getDescription());
                intent.putExtra("LIKES", picture.getLikeNumber());
                Explode explode = new Explode();
                explode.setDuration(1000);
                activity.getWindow().setExitTransition(explode);
                activity.startActivity(intent,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(activity,v,
                                activity.getString(R.string.transition_name_picture)).toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }


    public class PictureViewHolder extends RecyclerView.ViewHolder{

        private ImageView pictureCard;
        private TextView userNameCard;
        private TextView timeCard;
        private TextView likeNumberCard;


        public PictureViewHolder(View itemView) {
            super(itemView);

            pictureCard = (ImageView) itemView.findViewById(R.id.individualPictureCard);
            userNameCard = (TextView) itemView.findViewById(R.id.userNameCard);
            timeCard = (TextView) itemView.findViewById(R.id.timeCard);
            likeNumberCard = (TextView) itemView.findViewById(R.id.likeNumberCard);
        }

    }

}
