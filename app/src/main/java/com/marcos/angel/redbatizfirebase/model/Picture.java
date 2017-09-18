package com.marcos.angel.redbatizfirebase.model;

/**
 * Created by angel on 20/08/2017.
 */

public class Picture {
    private String id;
    private String picture;
    private String userName;
    private String time;
    private String likeNumber;
    private String title;
    private String description;

    public Picture(String id, String picture, String userName, String likeNumber, String time, String title, String description) {
        this.id = id;
        this.picture = picture;
        this.userName = userName;
        this.likeNumber = likeNumber;
        this.time = time;
        this.title = title;
        this.description = description;
    }

    public Picture( String picture, String userName, String likeNumber, String time){
        this.picture = picture;
        this.userName = userName;
        this.likeNumber = likeNumber;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Picture(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
