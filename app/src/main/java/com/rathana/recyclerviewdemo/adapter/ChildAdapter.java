package com.rathana.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rathana.recyclerviewdemo.R;
import com.rathana.recyclerviewdemo.model.ChildModel;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    List<ChildModel> children;
    Context mContext;

    public ChildAdapter(List<ChildModel> children, Context mContext) {
        this.children = children;
        this.mContext = mContext;
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding(children.get(i));
        viewHolder.itemView.setOnClickListener(v->{

        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext)
                .inflate(R.layout.child_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.childTitle);
        }

        void binding(ChildModel child){
            if(child!=null){
                image.setImageResource(child.getImage());
                title.setText(child.getTitle());
            }
        }
    }
}
