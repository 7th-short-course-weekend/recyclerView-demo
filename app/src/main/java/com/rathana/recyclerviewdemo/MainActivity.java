package com.rathana.recyclerviewdemo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rathana.recyclerviewdemo.adapter.ContactAdapter;
import com.rathana.recyclerviewdemo.callback.OnItemAdapterClickListener;
import com.rathana.recyclerviewdemo.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
implements OnItemAdapterClickListener {

    RecyclerView recyclerView;
    ContactAdapter adapter;
    List<Contact> contacts=new ArrayList<Contact>();

    FloatingActionButton btnAddNewItem;
    static final int ADD_ITEM_CODE=1;
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

        btnAddNewItem.setOnClickListener(v->{
            Intent intent=new Intent(this, AddItemActivity.class);
            startActivityForResult(intent,ADD_ITEM_CODE);
        });

        getContacts();
    }


    private void getContacts(){
        for(int i=0;i<100;i++){
            this.contacts.add(new Contact(
                    R.drawable.panda_cub,
                    "Admin "+i,
                    "admin"+i+"@gmail.com"
                    ));
        }
        adapter.notifyDataSetChanged();
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
