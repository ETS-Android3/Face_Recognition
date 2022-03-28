package com.atharvakale.facerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class form extends AppCompatActivity {
    
    Button Submit;
    EditText nameEntry , emailEntry , locationEntry , phoneEntry;

    formdatabase userForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        
        Submit = findViewById(R.id.SubmitButton);
        nameEntry = findViewById(R.id.nameEntry);
        emailEntry = findViewById(R.id.emailEntry);
        locationEntry = findViewById(R.id.LocationEntry);
        phoneEntry = findViewById(R.id.phoneEntry);
        userForm = new formdatabase(this);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = nameEntry.getText().toString().trim();
                String Email = emailEntry.getText().toString().trim();
                String Phone = phoneEntry.getText().toString().trim();
                String Location = locationEntry.getText().toString().trim();

                Boolean checkData = userForm.InsertData(Name , Email , Phone , Location);

                if(Name.equals("") || Email.equals("") || Location.equals("")  || Phone.equals(""))
                {
                    Toast.makeText(form.this, "Please Fill All Details First", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    if(checkData==true)
                    {
                        Intent intent = new Intent(getApplicationContext() , child.class);
                        startActivity(intent);
                        Toast.makeText(form.this, "Thank you for register", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(form.this, "Can't upload", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}