package com.atharvakale.facerecognition;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME  = "UserManager.db";

    //Database Table Name
    private static final String TABLE_USER = "User";

    //User table column name
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    //create sql table
    private String CREATE_USER_TABLE = "CREATE TABLE "+ TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT"+")";

    //Drop SQL table
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS "+TABLE_USER;

    //Constructor
    public Database(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Drop Table is exists
        sqLiteDatabase.execSQL(DROP_USER_TABLE);

        //Create table again
        onCreate(sqLiteDatabase);
    }

    public void addUser(user user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME , user.getName());
        values.put(COLUMN_USER_EMAIL , user.getEmail());
        values.put(COLUMN_USER_PASSWORD , user.getPassword());

        db.insert(TABLE_USER , null , values);
        db.close();
    }

    public boolean checkuser(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where user_email = ?" , new String[] {email});

        if (cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkusernamepassworword(String email , String password)
    {

        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from User where user_email = ? and user_password = ?" , new String[] {email , password});

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
