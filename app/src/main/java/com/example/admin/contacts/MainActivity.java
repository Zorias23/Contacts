package com.example.admin.contacts;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etFirstName;
    EditText etLastName;
    EditText etPhoneNumber;
    EditText etAltPhoneNumber;
    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

    }

    public void addContact(View view) {
        String fname;
        String lname;
        String phoneNumber;
        String altPhoneNumber;
        long returnVal = -1;
        fname = etFirstName.getText().toString();
        lname = etLastName.getText().toString();
        phoneNumber = "" + etPhoneNumber.getText().toString();
        altPhoneNumber = "" + etAltPhoneNumber.getText().toString();
        Contact contact = new Contact(fname, lname, phoneNumber, altPhoneNumber);
        ContentValues values = new ContentValues();
        values.put("firstName", fname);
        values.put("lastName", lname);
        values.put("phoneNumber", phoneNumber);
        values.put("altPhoneNumber", altPhoneNumber);
        getContentResolver().insert(ContactProvider.CONTENT_URI, values);
        //returnVal = myDB.saveContact(contact);
        /*
        if (returnVal > 0) {
            Toast.makeText(MainActivity.this, "Successfully entered " + contact.getFirstName() + "!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
        }*/
        //just going to write to the logs for now
        //myDB.getContactsAsList();
    }
    public void bindViews()
    {
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etAltPhoneNumber = findViewById(R.id.etAltPhoneNumber);
    }
    //just for my logging sanity purposes
    private void printRows()
    {
        myDB = new DatabaseHelper(this);
        myDB.getContactsAsList();
    }

    public void viewContacts(View view) {
        Cursor c = getContentResolver().query(ContactProvider.CONTENT_URI, null, null, null, null);
        if(c.moveToFirst())
        {
            do {
                //Contact contact = new Contact(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                Log.d("MainActivity", "viewContacts: " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3) + " " + c.getString(4));
                //contactsList.add(contact);
            }while(c.moveToNext());

        }
        myDB = new DatabaseHelper(this);
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        contactList = myDB.getContactsAsList();
        Intent intent = new Intent(this, ViewContactsActivity.class);
        intent.putParcelableArrayListExtra("contacts", contactList);
        startActivity(intent);
    }


}
