package com.atharvakale.facerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginpage extends AppCompatActivity {

    EditText emailEntry , password;

    Button login ;

    TextView registerText;

    user user;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        emailEntry = findViewById(R.id.EmailId);
        password = findViewById(R.id.password);

        login = findViewById(R.id.button1);

        registerText = findViewById(R.id.registerText);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginpage.this , registerpage.class);
                startActivity(intent);
            }
        });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String Email = emailEntry.getText().toString().trim();
               String Password = password.getText().toString().trim();


               user = new user();
               database = new Database(loginpage.this);

               if (Email.equals("") || Password.equals(""))
               {
                   Toast.makeText(loginpage.this, "please fill all fields", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Boolean result =  database.checkusernamepassworword(Email , Password);
                   if(result==true)
                   {
                       Intent intent = new Intent(getApplicationContext() , child.class);
                       startActivity(intent);
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(), "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                   }
               }
           }
       });

    }
}