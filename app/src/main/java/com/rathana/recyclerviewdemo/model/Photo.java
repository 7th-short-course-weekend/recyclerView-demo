package com.rathana.recyclerviewdemo.model;

import android.support.annotation.DrawableRes;

public class Photo {

    @DrawableRes
    private int image;
    private String title;

    public Photo() {
    }

    public Photo(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
