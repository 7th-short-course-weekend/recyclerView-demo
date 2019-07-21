package com.rathana.recyclerviewdemo.adapter;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rathana.recyclerviewdemo.R;
import com.rathana.recyclerviewdemo.model.Photo;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    List<Photo> photos;
    Context context;
    public GridAdapter(List<Photo> photos, Context context) {
        this.photos = photos;
        this.context=context;
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.photo_item_layout,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.image.getLayoutParams().width=getScreenSizeWithTwoColumns();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Photo photo=photos.get(i);
        viewHolder.title.setText(photo.getTitle());
        viewHolder.image.setImageResource(photo.getImage());
    }

    public void addMoreItems(List<Photo> photos) {
        int previousSize=this.photos.size();
        this.photos.addAll(photos);
        notifyItemRangeInserted(previousSize,photos.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);

        }
    }

    public int getScreenSizeWithTwoColumns(){
        Display display = ((AppCompatActivity)context).getWindowManager().getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);
        return size.x/2;
    }
}
