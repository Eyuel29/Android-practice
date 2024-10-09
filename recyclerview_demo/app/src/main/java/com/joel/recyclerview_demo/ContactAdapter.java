package com.joel.recyclerview_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    String[] contactNames, contactNumbers;
    Context context;

    public ContactAdapter(Context context, String[] contactNames, String[] contactNumbers){
        this.context = context;
        this.contactNames = contactNames;
        this.contactNumbers = contactNumbers;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item_layout,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.contactNameView.setText(contactNames[position]);
        holder.contactNumberView.setText(contactNumbers[position]);

        holder.contactsHolderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You've selected " + holder.contactNameView.getText(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactNumbers.length;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView contactNameView, contactNumberView;
        RelativeLayout contactsHolderView;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            contactNameView = itemView.findViewById(R.id.contactName);
            contactNumberView = itemView.findViewById(R.id.contactDetail);
            contactsHolderView = itemView.findViewById(R.id.listItemHolder);
        }
    }

}