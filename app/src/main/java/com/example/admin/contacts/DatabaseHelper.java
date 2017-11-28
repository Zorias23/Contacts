package com.example.admin.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Contact.db";
    public static final String TABLE_NAME = "Contacts";
    public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    public static final String COLUMN_PHONE_NUMBER = "PHONE_NUMBER";
    public static final String COLUMN_ALT_PHONE_NUMBER = "ALT_PHONE_NUMBER";
    public static final String COLUMN_PRIMARY_KEY = "Contact_PK_ID";
    public static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1 , " +
                 COLUMN_FIRST_NAME + " TEXT, " + COLUMN_LAST_NAME + " TEXT, " + COLUMN_PHONE_NUMBER + " TEXT, " + COLUMN_ALT_PHONE_NUMBER + " TEXT)";
             sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override //called whenever we change the database version, not first creation where we originally set it, but if we change it again
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public long saveContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, contact.getFirstName());
        contentValues.put(COLUMN_LAST_NAME, contact.getLastName());
        contentValues.put(COLUMN_PHONE_NUMBER, contact.getPhoneNumber());
        contentValues.put(COLUMN_ALT_PHONE_NUMBER, contact.getAltPhoneNumber());

       long row =  db.insert(TABLE_NAME, null, contentValues);  //returns row number, returns -1 if failed
        return row;
    }
    public ArrayList<Contact> getContactsAsList()
    {
        ArrayList<Contact> contactsList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(QUERY, null);//because we're doing Select *, I can pass null, if not, i have to have column names for second argument
        //cursor returns each row of data so I have to loop through results

        if(cursor.moveToFirst())
        {
            do {
                Contact contact = new Contact(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                Log.d("DatabaseHelper", "getContactsAsList: " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3));
                contactsList.add(contact);
            }while(cursor.moveToNext());

        }
        return contactsList;
    }
    public boolean containsRows()
    {
        boolean containsRows = false;

        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(QUERY, null);//because we're doing Select *, I can pass null, if not, i have to have column names for second argument
        //cursor returns each row of data so I have to loop through results

        if(cursor.moveToFirst())
        {
            do {
                containsRows = true;
                break;
            }while(cursor.moveToNext());

        }
        return containsRows;
    }
    public List<String> getCategoriesAsList()
    {
        /*
        List<String> categoryList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(QUERY, null);//because we're doing Select *, I can pass null, if not, i have to have column names for second argument
        //cursor returns each row of data so I have to loop through results

        if(cursor.moveToFirst())
        {
            do {
                String s =  cursor.getString(1);
                Log.d("DatabaseHelper", "getCategoriesAsList: " + cursor.getString(1));
                //we don't want duplicate categories, more than one animal can be in a category
                if (!categoryList.contains(s))
                {
                    categoryList.add(s);
                }

            }while(cursor.moveToNext());

        } */
        return new ArrayList<String>();
    }
    /*
    public ArrayList<Animal> getAnimalsFromCategoriesAsList(String category)
    {
        ArrayList<Animal> animalList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE CATEGORY = '" + category + "' ";
        Cursor cursor = database.rawQuery(QUERY, null);//because we're doing Select *, I can pass null, if not, i have to have column names for second argument
        //cursor returns each row of data so I have to loop through results

        if(cursor.moveToFirst())
        {
            do {
                Animal a = new Animal(cursor.getString(0), cursor.getString(1));
                Log.d("DatabaseHelper", "getAnimalsFromCategoriesAsList: " + cursor.getString(0) + " " + cursor.getString(1));
                animalList.add(a);
            }while(cursor.moveToNext());

        }
        return animalList;
    }*/
    public Cursor getContactsAsCursor()
    {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(QUERY, null);//because we're doing Select *, I can pass null, if not, i have to have column names for second argument
        //cursor returns each row of data so I have to loop through results
        return cursor;
    }
}
