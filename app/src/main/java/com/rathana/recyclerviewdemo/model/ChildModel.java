package com.rathana.recyclerviewdemo.model;

public class ChildModel {

    private String title;
    private int image;

    public ChildModel() { }

    public ChildModel(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
