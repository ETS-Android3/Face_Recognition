package com.atharvakale.facerecognition;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.checkerframework.checker.units.qual.C;

public class formdatabase  extends SQLiteOpenHelper {
    public formdatabase(Context context) {
        super(context, "form.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("CREATE TABLE USERINFO(name TEXT primary key , email TEXT , Phone TEXT , Location TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

        DB.execSQL("DROP TABLE IF EXISTS USERINFO");

    }

    public boolean InsertData(String Name , String Email , String Phone , String Location)
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME" , Name);
        contentValues.put("Email" , Email);
        contentValues.put("Phone" , Phone);
        contentValues.put("Location" , Location);

        long result = DB.insert("USERINFO" , null , contentValues);

        if (result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

    public boolean checkuser(String EmailCount)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from User where user_email = ?" , new String[] {EmailCount});

        if (cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
