package com.rathana.recyclerviewdemo;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.paginate.Paginate;
import com.paginate.recycler.LoadingListItemSpanLookup;
import com.rathana.recyclerviewdemo.adapter.ContactAdapter;
import com.rathana.recyclerviewdemo.callback.OnItemAdapterClickListener;
import com.rathana.recyclerviewdemo.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountedCompleter;

public class MainActivity extends AppCompatActivity
implements OnItemAdapterClickListener {

    RecyclerView recyclerView;
    ContactAdapter adapter;
    List<Contact> contacts=new ArrayList<Contact>();
    List<Contact> originalContactList=new ArrayList<Contact>();
    FloatingActionButton btnAddNewItem;
    static final int ADD_ITEM_CODE=1;

    private int currentPage=1;
    private int totalPage = 10;
    private boolean isLoading=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup recycler View
        recyclerView=findViewById(R.id.rvListItems);
        btnAddNewItem=findViewById(R.id.btnAddNewItem);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration=new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        adapter=new ContactAdapter(contacts,this);
        recyclerView.setAdapter(adapter);

        //setup pagination
        isLoading=false;
        currentPage=1;
        Paginate.with(recyclerView,callback)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(null)
                .setLoadingListItemSpanSizeLookup(new LoadingListItemSpanLookup() {
                    @Override
                    public int getSpanSize() {
                        return 1;
                    }
                })
                .build();
        //callback.onLoadMore();
        //getContacts();

        btnAddNewItem.setOnClickListener(v->{
            Intent intent=new Intent(this, AddItemActivity.class);
            startActivityForResult(intent,ADD_ITEM_CODE);
        });


    }

    private static final String TAG = "MainActivity";

    //pagination callback
    Paginate.Callbacks callback=new Paginate.Callbacks() {
        @Override
        public void onLoadMore() {
            Log.e(TAG, "onLoadMore: "+currentPage );
           // if(isLoading){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isLoading=true;
                        getContacts();
                        currentPage++;
                    }
                },600);
            }
        //}

        @Override
        public boolean isLoading() {
            return isLoading;
        }

        @Override
        public boolean hasLoadedAllItems() {
            return currentPage==10;
        }
    };


    private void getContacts(){
        for(int i=0;i<15;i++){
            this.contacts.add(new Contact(
                    R.drawable.panda_cub,
                    "Admin "+i,
                    "admin"+i+"@gmail.com"
                    ));
        }
        isLoading=false;
        this.originalContactList.addAll(contacts);
        adapter.notifyDataSetChanged();
    }



    //create option menu searchView


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        MenuItem searchItem= menu.findItem(R.id.app_bar_search);
        SearchView searchView=(SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                doSearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        //close search view
        ImageView closeSearchIcon= searchView.findViewById(R.id.search_close_btn);
        closeSearchIcon.setOnClickListener(v->{
            EditText edSearch= searchView.findViewById(R.id.search_src_text);
            //clear edit text
            edSearch.setText("");
            //clear query search
            searchView.setQuery("",false);
            //collapse the action view
            searchView.onActionViewCollapsed();
            //collapse search widget
            searchItem.collapseActionView();

            contacts.clear();
            adapter.addItems(this.originalContactList);

        });

        return true;
    }

    private void doSearch(String text){
        if(text.equals("") || text==null){
            return;
        }

        this.contacts.clear();
        //adapter.notifyDataSetChanged();
        for(Contact contact : originalContactList){
            if(contact.getName().matches(text)){
                this.contacts.add(contact);
            }
        }
        if(this.contacts.size()>0){
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemEdit(Contact contact, int position) {

        Toast.makeText(this, ""+contact.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemRemove(Contact contact, int position) {
        adapter.removeItem(contact,position);
        ///....

        Toast.makeText(this, ""+contact.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_ITEM_CODE && resultCode==RESULT_OK){
            if(data!=null){
                Contact contact=data.getParcelableExtra("contact");
                adapter.setContact(contact);
                smoothScroll(0);
            }
        }


    }

    private void smoothScroll(int position){
        recyclerView.smoothScrollToPosition(position);
    }
}
