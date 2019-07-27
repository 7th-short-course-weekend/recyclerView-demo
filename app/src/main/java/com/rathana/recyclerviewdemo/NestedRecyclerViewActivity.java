package com.rathana.recyclerviewdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.rathana.recyclerviewdemo.adapter.ParentAdapter;
import com.rathana.recyclerviewdemo.model.ChildModel;
import com.rathana.recyclerviewdemo.model.ParentModel;
import com.rathana.recyclerviewdemo.utils.LoadMoreLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class NestedRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView rvParent;
    private ParentAdapter parentAdapter;
    private List<ParentModel> parent = new ArrayList<ParentModel>();
    private int currentPage=1;
    private static final String TAG = "NestedActivity";
    private LoadMoreLinearLayoutManager layoutManager;

//    private Runnable loadDataRunnable=new Runnable() {
//        @Override
//        public void run() {
//            getData();
//        }
//    };
    private Runnable loadMoreDataRunnalbe=new Runnable() {
        @Override
        public void run() {
            getMoreData();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_recycler_view);

        getData();
        //new Handler().postDelayed(loadDataRunnable,500);
        rvParent = findViewById(R.id.rvParent);
        layoutManager=new LoadMoreLinearLayoutManager(this);
        rvParent.setLayoutManager(layoutManager);
        parentAdapter = new ParentAdapter(parent, this);
        rvParent.setAdapter(parentAdapter);
        parentAdapter.setCanLoadMore(true);
        //load more items
        layoutManager.setLoadMOreListener(new LoadMoreLinearLayoutManager.OnLoadMOreListener() {
            @Override
            public void onLoadMore() {
                currentPage++;
                Log.e(TAG, "onLoadMore: "+currentPage );
                new Handler().postDelayed(loadMoreDataRunnalbe,500);
            }
        });

        layoutManager.loadingFinished();
        parentAdapter.setCanLoadMore(true);
    }

    private void getData() {
        //create data for parent
        for (int i = 0; i < 10; i++) {
            //create data for children
            List<ChildModel> childModels = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                childModels.add(new ChildModel("google drive", R.drawable.cat_3));
                childModels.add(new ChildModel("google drive", R.drawable.cat));
                childModels.add(new ChildModel("google drive", R.drawable.cat_2));
                childModels.add(new ChildModel("google drive", R.drawable.panda_cub));
            }
            this.parent.add(new ParentModel("parent " + i, childModels));
        }
    }

    private void getMoreData(){
        List<ParentModel> parentModels=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //create data for children
            List<ChildModel> childModels = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                childModels.add(new ChildModel("google drive", R.drawable.cat_3));
                childModels.add(new ChildModel("google drive", R.drawable.cat));
                childModels.add(new ChildModel("google drive", R.drawable.cat_2));
                childModels.add(new ChildModel("google drive", R.drawable.panda_cub));
            }
            parentModels.add(new ParentModel("parent " + i, childModels));
        }
        parentAdapter.addMoreItems(parentModels);
        parentAdapter.setCanLoadMore(true);
        layoutManager.loadingFinished();
    }
}










