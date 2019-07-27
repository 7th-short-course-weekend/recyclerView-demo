package com.rathana.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rathana.recyclerviewdemo.R;
import com.rathana.recyclerviewdemo.model.ParentModel;

import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ParentModel> parent;
    private Context mContext;
    private RecyclerView.RecycledViewPool viewPool
            = new RecyclerView.RecycledViewPool();
    private boolean canLoadMore = false;

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
    }

    public static final int ITEM_PROGRESS = -1;
    public static final int ITEM_LAYOUT = -2;

    public ParentAdapter(List<ParentModel> parent, Context mContext) {
        this.parent = parent;
        this.mContext = mContext;
    }

    @Override
    public int getItemCount() {
        if (canLoadMore)
            return parent.size() + 1;
        else
            return parent.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < parent.size())
            return ITEM_LAYOUT;
        else
            return ITEM_PROGRESS;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == ITEM_LAYOUT) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.parent_layout, viewGroup, false);
            return new ViewHolder(view);
        } else if (viewType==ITEM_PROGRESS){
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.progressbar_layout, viewGroup, false);
            return new ProgressViewHolder(view);
        }

        return super.createViewHolder(viewGroup,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof ViewHolder)
            ((ViewHolder)viewHolder).binding(parent.get(i));
        else if(viewHolder instanceof ProgressViewHolder){
            //any binding to progress View Holder
        }
    }


    private static final String TAG = "ParentAdapter";
    public void addMoreItems(List<ParentModel> parent){
        int previousSize = this.parent.size();
        this.parent.addAll(parent);
        //notifyDataSetChanged();
        Log.e(TAG, "addMoreItems: previous Size "+previousSize );
        Log.e(TAG, "addMoreItems: parent Size "+this.parent.size() );
        notifyItemRangeInserted(previousSize,parent.size());
    }

    class ProgressViewHolder extends RecyclerView.ViewHolder{
        public ProgressViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, btnMore;
        private RecyclerView rvChildren;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.parentTitle);
            btnMore = itemView.findViewById(R.id.btnMore);
            rvChildren = itemView.findViewById(R.id.rvChildren);
        }

        void binding(ParentModel parent) {
            if (parent != null) {
                title.setText(parent.getTitle());
                LinearLayoutManager layoutManager = new LinearLayoutManager(
                        mContext,
                        LinearLayoutManager.HORIZONTAL,
                        false
                );
                rvChildren.setLayoutManager(layoutManager);
                //todo create child adapter
                ChildAdapter childAdapter = new ChildAdapter(
                        parent.getChildren(),
                        mContext);
                rvChildren.setAdapter(childAdapter);
                rvChildren.setRecycledViewPool(viewPool);
            }
        }
    }

}
