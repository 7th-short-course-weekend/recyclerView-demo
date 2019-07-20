package com.rathana.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.rathana.recyclerviewdemo.R;
import com.rathana.recyclerviewdemo.callback.OnItemAdapterClickListener;
import com.rathana.recyclerviewdemo.model.Contact;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    List<Contact> contacts;
    Context context;
    OnItemAdapterClickListener itemAdapterClickListener;
    public ContactAdapter(List<Contact> contacts,Context context) {
        this.contacts = contacts;
        this.context=context;
        this.itemAdapterClickListener=(OnItemAdapterClickListener) context;
    }

    public void setItemAdapterClickListener(OnItemAdapterClickListener itemAdapterClickListener) {
        this.itemAdapterClickListener = itemAdapterClickListener;
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Contact contact= contacts.get(i);
        viewHolder.name.setText(contact.getName());
        viewHolder.email.setText(contact.getEmail());
        viewHolder.image.setImageResource(contact.getImage());

        viewHolder.itemView.setOnClickListener(v->{
            Toast.makeText(context, "itemView Clicked",
                    Toast.LENGTH_SHORT).show();
        });

        viewHolder.btnMore.setOnClickListener(v->{

            PopupMenu menu=new PopupMenu(context,v);
            menu.inflate(R.menu.popup_menu);
            menu.setOnMenuItemClickListener(item->{
                switch (item.getItemId()){
                    case R.id.remove:
                        itemAdapterClickListener.onItemRemove(contact,viewHolder.getAdapterPosition());
                        return true;
                    case R.id.edit:
                        itemAdapterClickListener.onItemEdit(contact,viewHolder.getAdapterPosition());
                        return true;
                    default : return false;
                }
            });

            menu.show();
            Toast.makeText(context, "button more clicked",
                    Toast.LENGTH_SHORT).show();
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_layout,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    public void removeItem(Contact contact, int position) {
        this.contacts.remove(contact);
        notifyItemRemoved(position);
    }

    public void setContact(Contact contact) {
        this.contacts.add(0,contact);
        notifyItemInserted(0);

    }

    //hold all views that exist in the item layout
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,email;
        CircleImageView image;
        ImageView btnMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            btnMore=itemView.findViewById(R.id.btnMore);
        }
    }
}



/**
 - Create new inner class recyclerview ViewHolder > extends RecyclerView.ViewHolder
 - create new class that extends RecyclerView.Adapter
 - overriding three methods
     1 getItemCount()
     2 onBindViewHolder()
     3 onCreateViewHolder()

 */