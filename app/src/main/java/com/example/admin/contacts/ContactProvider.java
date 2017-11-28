package com.example.admin.contacts;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Admin on 11/27/2017.
 */

public class ContactProvider extends ContentProvider {

    public static final String AUTHORITY = "com.example.admin.contacts.Contacts";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/contacts");
    public static final String CONTENT_TYPE_PLATES = "vnd.android.cursor.dir/vnd.example.contacts";
    public static final String CONTENT_TYPE_PLATE = "vnd.android.cursor.item/vnd.example.contact";
    DatabaseHelper myDB;

        private static final UriMatcher sUriMatcher;

        private static final int CONTACTS_ALL = 2;
        private static final int CONTACTS_ONE = 3;

        static {
            sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
            sUriMatcher.addURI(AUTHORITY, "contacts", CONTACTS_ALL);
            sUriMatcher.addURI(AUTHORITY, "contacts/#", CONTACTS_ONE);
        }
    @Override
    public boolean onCreate() {

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        myDB = new DatabaseHelper(getContext());
        Cursor c = myDB.getContactsAsCursor();
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        // you cannot insert a bunch of values at once so throw exception
        if (sUriMatcher.match(uri) != CONTACTS_ALL) {
            throw new IllegalArgumentException(" Unknown URI: " + uri);
        }

        // Insert once row
        myDB = new DatabaseHelper(getContext());
        Contact contact = new Contact(contentValues.getAsString("firstName"), contentValues.getAsString("lastName"), contentValues.getAsString("phoneNumber"),contentValues.getAsString("altPhoneNumber"));
        long rowId = myDB.saveContact(contact);
        if (rowId> 0) {
            Toast.makeText(getContext(), "Successfully entered " + contact.getFirstName() + "!", Toast.LENGTH_LONG).show();
            return null;
        }
        throw new IllegalArgumentException("<Illegal>Unknown URI: " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
