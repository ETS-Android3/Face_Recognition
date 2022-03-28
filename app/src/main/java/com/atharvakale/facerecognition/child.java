package com.atharvakale.facerecognition;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

    ImageView searchIcon , viewIcon;
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
        viewIcon = findViewById(R.id.viewIcon);
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
                        Intent intent = new Intent(child.this , MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        viewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder MyAlert = new AlertDialog.Builder(child.this);
                MyAlert.setTitle("Nearby Police Station");
                MyAlert.setMessage("Delhi Police Station \n phone no: - 57484394937 \n\n\n Bhubaneswar Police Station \n phone no: - 3647383837");
                MyAlert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(child.this, "Thank You", Toast.LENGTH_SHORT).show();
                    }
                });
                MyAlert.show();
            }
        });
    }
}