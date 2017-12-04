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
        contentValues.put(dbhelper.getColumnUserName(), user.getUsernama());
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
        if (cursorCount>0){
            return true;
        }else {
            return false;

        }


    }  public long getUserId(String nama, String pass){
        tryGetAcces(dbhelper.getReadableDatabase());
        String Col []= {
                dbhelper.getColumnUserId(), dbhelper.getColumnUserName(), dbhelper.getColumnUserName(),
                dbhelper.getColumnUserPassword(), dbhelper.getColumnImage(),  dbhelper.getColumnName(),
                dbhelper.getColumnEmail(), dbhelper.getColumnPhone()
        };
        String selection = dbhelper.getColumnUserName()+" = ? AND "+dbhelper.getColumnUserPassword()+" = ?";
        Cursor cursor = sqLiteDatabase.query(dbhelper.getTableUser(),Col ,selection,
                new String[]{nama, pass},
                null, null,null,null);

        int cursorCount = cursor.getCount();
        cursor.moveToPosition(0);
        long id = cursor.getLong(cursor.getColumnIndex(dbhelper.getColumnUserId()));
        cursor.close();
        sqLiteDatabase.close();
        if (cursorCount>0){
            return id;
        }else {
            return 0;

        }


    }


    public void updateData(long id, byte [] images , String nama, String email, String phoneNumber){
        tryGetAcces(dbhelper.getWritableDatabase());
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.getColumnImage(), images);
        contentValues.put(dbhelper.getColumnName(), nama);
        contentValues.put(dbhelper.getColumnEmail(), email);
        contentValues.put(dbhelper.getColumnPhone(), phoneNumber);
        sqLiteDatabase.update(dbhelper.getTableUser(), contentValues,
                dbhelper.getColumnUserId()+" = ? ", new String[]{String.valueOf(id)});
        Toast.makeText(context, "Success! Updating Profil", Toast.LENGTH_SHORT).show();


    }


    public User cursorToUser(Cursor cursor){
        User tempuser = new User();
        tempuser.setUsernama(cursor.getString(cursor.getColumnIndex(dbhelper.getColumnUserName())));
        tempuser.setEmail(cursor.getString(cursor.getColumnIndex(dbhelper.getColumnEmail())));
        tempuser.setNama(cursor.getString(cursor.getColumnIndex(dbhelper.getColumnName())));
        tempuser.setPhonenumber(cursor.getString(cursor.getColumnIndex(dbhelper.getColumnPhone())));
        tempuser.setBytes(cursor.getBlob(cursor.getColumnIndex(dbhelper.getColumnImage())));
        return tempuser;
    }

    public User getDataUser(long id){
        tryGetAcces(dbhelper.getReadableDatabase());
        User usr = new User();
        String Col []= {
                dbhelper.getColumnUserId(), dbhelper.getColumnUserName(), dbhelper.getColumnUserName(),
                dbhelper.getColumnUserPassword(), dbhelper.getColumnImage(),  dbhelper.getColumnName(),
                dbhelper.getColumnEmail(), dbhelper.getColumnPhone()
        };
        Cursor cursor = sqLiteDatabase.query(dbhelper.getTableUser(), Col , dbhelper.getColumnUserId()+" = ?" , new String[]{String.valueOf(id)},
                null, null,null,null);
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            usr= cursorToUser(cursor);
        }
        return usr;
    }


    private void tryGetAcces (SQLiteDatabase sqLiteDatabase){
        try {
            this.sqLiteDatabase=sqLiteDatabase;
        }catch (SQLiteException e){
            Toast.makeText(context, "gagal dapat acces database", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean cekPassword(String pass){
            tryGetAcces(dbhelper.getReadableDatabase());
            Cursor cursor = sqLiteDatabase.query(dbhelper.getTableUser(), new String[] {dbhelper.getColumnUserId()},
                    dbhelper.getColumnUserPassword()+" = ?", new String[]{pass}, null, null, null );
            int cursorCount= cursor.getCount();
            cursor.close();
        sqLiteDatabase.close();
        if (cursorCount>0){
                return true;
            }else {
                return false;
            }

    }
}
