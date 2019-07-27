package com.rathana.recyclerviewdemo.model;

import java.util.List;

public class ParentModel {

    private String title;
    private List<ChildModel> children;

    public ParentModel() {}

    public ParentModel(String title, List<ChildModel> children) {
        this.title = title;
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ChildModel> getChildren() {
        return children;
    }

    public void setChildren(List<ChildModel> children) {
        this.children = children;
    }
}
