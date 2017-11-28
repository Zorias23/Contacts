package com.example.admin.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewContactsActivity extends AppCompatActivity {
    ArrayList<Contact> contacts = new ArrayList<>();
    ListView lvCeleb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);
        contacts =  getIntent().getExtras().getParcelableArrayList("contacts");
        RecyclerView recyclerView = findViewById(R.id.rvMain);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        ContactsRecyclerAdapter contactsRecyclerAdapter =
                new ContactsRecyclerAdapter(contacts);

        recyclerView.setLayoutManager(layoutManager); //need layoutManager
        recyclerView.setItemAnimator(itemAnimator); //don't need this but it allows animation for each item
        recyclerView.setAdapter(contactsRecyclerAdapter); //need adapter
    }
}
