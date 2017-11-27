package com.example.z.counter.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.example.z.counter.model.User;

/**
 * Created by z on 23/11/17.
 */

public class Userhelper {
    Context context;
    SQLiteDatabase sqLiteDatabase;
    DBhelper dbhelper;
    User user;
    public Userhelper(Context context) {
        this.context = context;
        dbhelper = new DBhelper(context);

    }


    public boolean inputNewUser(User user){
        tryGetAcces(dbhelper.getWritableDatabase());
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.getColumnUserName(), user.getNama());
        contentValues.put(dbhelper.getColumnUserPassword(), user.getPassword());
        long insrtID = sqLiteDatabase.insert(dbhelper.getTableUser(), null, contentValues);
        sqLiteDatabase.close();
        if (insrtID>-1){
           return true;
        }else {
            return false;
        }
    }

    public boolean loginUserCheck(String nama, String pass){
        tryGetAcces(dbhelper.getReadableDatabase());
        String selection = dbhelper.getColumnUserName()+" = ? AND "+dbhelper.getColumnUserPassword()+" = ?";
        Cursor cursor = sqLiteDatabase.query(dbhelper.getTableUser(), new String[]{dbhelper.getColumnUserId()},selection,
                new String[]{nama, pass},
                null, null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;

        }
    }


    private void tryGetAcces (SQLiteDatabase sqLiteDatabase){
        try {
            this.sqLiteDatabase=sqLiteDatabase;
        }catch (SQLiteException e){
            Toast.makeText(context, "gagal dapat acces database", Toast.LENGTH_SHORT).show();
        }
    }
}
