package com.atharvakale.facerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class registerpage extends AppCompatActivity {

    EditText nameEntry , EmailEntry , password , confirmPassword;
    Button register;
    TextView LoginText;

    user user;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);

        nameEntry = findViewById(R.id.nameEntry);
        EmailEntry = findViewById(R.id.mailEntry);
        password = findViewById(R.id.passwordEntry);
        confirmPassword = findViewById(R.id.password2Entry);

        register = findViewById(R.id.button1);

        LoginText = findViewById(R.id.loginText);

        LoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerpage.this , loginpage.class);
                startActivity(intent);
            }
        });

        user = new user();
        database = new Database(registerpage.this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = nameEntry.getText().toString().trim();
                String Email = EmailEntry.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String ConfirmPassword = confirmPassword.getText().toString().trim();


                if(Name.equals("") || Email.equals("") || Password.equals("") || ConfirmPassword.equals(""))
                {
                    Toast.makeText(registerpage.this, "Please Enter All fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!database.checkuser(EmailEntry.getText().toString().trim()))
                    {
                        user.setName(nameEntry.getText().toString().trim());
                        user.setEmail(EmailEntry.getText().toString().trim());
                        user.setPassword(password.getText().toString().trim());
                        database.addUser(user);

                        Toast.makeText(registerpage.this, "Registration Successfull", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(registerpage.this , loginpage.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(registerpage.this, "Email id named "+Email+" already exists", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}