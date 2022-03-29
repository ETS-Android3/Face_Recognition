package com.atharvakale.facerecognition;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class registerpage extends AppCompatActivity {

    EditText nameEntry , EmailEntry , password , confirmPassword , Phone;
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
                Email=Email.toLowerCase();
                String Password = password.getText().toString().trim();
                String ConfirmPassword = confirmPassword.getText().toString().trim();

                Transaction transaction1=new Transaction(Name, Email, Password,100L);
                Transaction transaction2 =new Transaction(Name, Email, Password,100L);
                Transaction transaction3 = new Transaction(Name, Email, Password,100L);
                Transaction transaction4 =new Transaction(Name, Email, Password,100L);

                Block firstBlock = new Block(0, Arrays.asList(transaction1, transaction2));
                System.out.println(firstBlock.hashCode());
                Block secondBlock = new Block(firstBlock.hashCode(), Arrays.asList(transaction3));
                System.out.println(secondBlock.hashCode());
                Block thirdBlock = new Block(secondBlock.hashCode(), Arrays.asList(transaction4));
                System.out.println(thirdBlock.hashCode());


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
                        Toast.makeText(registerpage.this, "This is ur "+secondBlock.hashCode()+" hash code", Toast.LENGTH_LONG).show();

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