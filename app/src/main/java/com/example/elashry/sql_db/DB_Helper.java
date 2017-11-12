package com.example.elashry.sql_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import java.util.ArrayList;

/**
 * Created by elashry on 11/12/2017.
 */

public class DB_Helper extends SQLiteOpenHelper {
    public static final String DBname="mydata.db";
    public static final String _ID = "_id";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String TABLE_NAME = "person";

    public DB_Helper(Context context) {

        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMAIL + " TEXT NOT NULL, " + NAME + " TEXT);");

    }
    public boolean insert(String name, String email) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(EMAIL, email);
        contentValue.put(NAME, name);
        long result=db.insert(TABLE_NAME, null, contentValue);
        if (result == -1) {
            return false;
        }else{
            return true;
        }
    }
    public ArrayList getdata(){
        ArrayList list=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            String m1=cursor.getString(0);
            String m2=cursor.getString(1);
            String m3=cursor.getString(2);
            list.add(m1+ ": "+ m2 + " -"+m3);
            cursor.moveToNext();

        }
        return list;
    }

    public boolean update_data(String id,String name, String email){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(EMAIL, email);
        contentValue.put(NAME, name);
        db.update(TABLE_NAME, contentValue,_ID +"= ?",new String[]{id});
       return true;
    }
    public Integer delete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
      return   db.delete(TABLE_NAME,_ID +"= ?",new String[]{id});

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
