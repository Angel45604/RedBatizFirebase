package com.marcos.angel.redbatizfirebase.model;

/**
 * Created by angel on 20/08/2017.
 */

public class Picture {

    private String picture;
    private String userName;
    private String time;
    private String likeNumber;

    public Picture(String picture, String userName, String time, String likeNumber) {
        this.picture = picture;
        this.userName = userName;
        this.time = time;
        this.likeNumber = likeNumber;
    }

    public String getPicture() {
        return picture;
    }

    public String getUserName() {
        return userName;
    }

    public String getTime() {
        return time;
    }

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }
}
