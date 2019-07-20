package com.rathana.recyclerviewdemo.callback;

import com.rathana.recyclerviewdemo.model.Contact;

public interface OnItemAdapterClickListener {

    void onItemRemove(Contact contact,int position);
    void onItemEdit(Contact contact,int position);

}
