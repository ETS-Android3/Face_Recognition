package com.atharvakale.facerecognition;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table userdetails(name TEXT primary key , gender TEXT , age TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("Drop table if exists userdetails");
    }

    public boolean insertData(String name , String gender , String age)
    {
        SQLiteDatabase DB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name" , name);
        contentValues.put("gender" , gender);
        contentValues.put("age" , age);
        long result = DB.insert("userdetails" , null , contentValues);

        if (result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean updateData(String name , String gender , String age) {
        SQLiteDatabase DB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gender", gender);
        contentValues.put("age", age);
        Cursor cursor = DB.rawQuery("select * from userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("userdetails", contentValues, "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public boolean deleteData(String name) {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("select * from userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("userdetails",  "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("select * from userdetails" , null);
        return cursor;
    }
}

