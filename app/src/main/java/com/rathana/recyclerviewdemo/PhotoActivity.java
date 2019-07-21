package com.rathana.recyclerviewdemo;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Display;
import android.widget.GridView;

import com.rathana.recyclerviewdemo.adapter.GridAdapter;
import com.rathana.recyclerviewdemo.adapter.StaggeredGridAdapter;
import com.rathana.recyclerviewdemo.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GridAdapter adapter;
    List<Photo> photos=new ArrayList<>();
    StaggeredGridAdapter staggeredGridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        //initUI();
        initUIWithStaggeredGrid();
        getPhotos();
    }

    private void initUI(){

        recyclerView=findViewById(R.id.rvPhotoList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter=new GridAdapter(photos,this);
        recyclerView.setAdapter(adapter);
    }

    private void initUIWithStaggeredGrid(){
        recyclerView=findViewById(R.id.rvPhotoList);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL));

        //adapter=new GridAdapter(photos,this);
        staggeredGridAdapter=new StaggeredGridAdapter(photos,this);
        recyclerView.setAdapter(staggeredGridAdapter);
    }

    private void getPhotos(){
        List<Photo> photosList=new ArrayList<>();
        for(int i=0;i<10;i++){
            photosList.add(new Photo(R.drawable.panda_cub,"Panda "+i));
            photosList.add(new Photo(R.drawable.cat,"Panda "+i));
            photosList.add(new Photo(R.drawable.cat_3,"Panda "+i));
            photosList.add(new Photo(R.drawable.image_1,"Panda "+i));
            photosList.add(new Photo(R.drawable.cat_2,"Panda "+i));
        }
        staggeredGridAdapter.addMoreItems(photosList);
    }




}
