package com.example.admin.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ViewContactActivity extends AppCompatActivity {
    EditText etFirstName;
    EditText etLastName;
    EditText etPhoneNumber;
    EditText etAltPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        Contact c = getIntent().getExtras().getParcelable("contact");
        etFirstName = findViewById(R.id.etFirstName2);
        etLastName = findViewById(R.id.etLastName2);
        etPhoneNumber = findViewById(R.id.etPhoneNumber2);
        etAltPhoneNumber = findViewById(R.id.etAltPhoneNumber2);
        etFirstName.setText(c.getFirstName());
        etLastName.setText(c.getLastName());
        etPhoneNumber.setText(c.getPhoneNumber());
        etAltPhoneNumber.setText(c.getAltPhoneNumber());

    }

    public void updateContact(View view) {
    }
}
