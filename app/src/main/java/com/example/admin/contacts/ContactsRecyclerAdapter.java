package com.example.admin.contacts;

import android.content.ContentProvider;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/14/2017.
 */

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ViewHolder>{

    public static ArrayList<Contact> contactList = new ArrayList<>();

    public List<Contact> getContactList() {
        return contactList;
    }



    public static final String TAG = "ContactsRecycler";

    @Override
    public ContactsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_recycler_item, null);
        return new ViewHolder(view);
    }
    public ContactsRecyclerAdapter(ArrayList<Contact> contactList) {
        this.contactList = contactList;
}

    public ContactsRecyclerAdapter()
    {

    }

    @Override
    public void onBindViewHolder(ContactsRecyclerAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Contact c  = contactList.get(position);
        if (c != null)
        {
            holder.tvFirstName.setText(c.getFirstName());
            holder.tvLastName.setText(c.getLastName());
            holder.tvPhoneNumber.setText(c.getPhoneNumber());
            holder.tvAltPhoneNumber.setText(c.getAltPhoneNumber());
        }

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView tvFirstName;
        private final TextView tvLastName;
        private final TextView tvPhoneNumber;
        private final TextView tvAltPhoneNumber;
        ContactsRecyclerAdapter a = new ContactsRecyclerAdapter();


        public ViewHolder(View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            tvAltPhoneNumber = itemView.findViewById(R.id.tvAltPhoneNumber);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            ArrayList<Contact> contacts = ContactsRecyclerAdapter.contactList;
            Log.d("ContactsRecyclerAdapter", "onClick: you clicked position " + getPosition());

            Intent intent = new Intent(view.getContext(), ViewContactActivity.class);
            intent.putExtra("contact", contacts.get(getPosition()));
            view.getContext().startActivity(intent);
        }
    }
}
