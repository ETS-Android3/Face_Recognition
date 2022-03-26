package com.atharvakale.facerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class child extends AppCompatActivity {

    Button face;
    EditText name , gender , age;

    ImageView searchIcon;
    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        face = findViewById(R.id.face);
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.age);
        searchIcon = findViewById(R.id.searchIcon);
        DB = new DBhelper(child.this);
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(child.this , MainActivity.class);
                startActivity(intent);
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText = name.getText().toString().trim();
                String genderText = gender.getText().toString().trim();
                String ageText = age.getText().toString().trim();

                boolean checkinsertdata = DB.insertData(nameText , genderText , ageText);
                if(nameText.isEmpty()||genderText.isEmpty()||ageText.isEmpty()) {
                    Toast.makeText(child.this, "Fill all details first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (checkinsertdata == true) {
                        Toast.makeText(child.this, "Missing child registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(child.this, "missing child found!!!!! Contact nearby police station or NGO", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}