package com.example.z.counter.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.example.z.counter.model.Match;

import java.util.ArrayList;

/**
 * Created by z on 27/11/17.
 */

public class ListHelper {
    DBhelper mdBhelper;
    Context context;
    SQLiteDatabase sqLiteDatabase;

    public ListHelper(Context context) {
        this.context = context;
        mdBhelper = new DBhelper(context);


    }

    public boolean inputMatchFt(String nteamA, String nteamB, int scA , int scB, String date , String matchname){
        tryGetAcces(mdBhelper.getWritableDatabase());
        ContentValues contentValues = new ContentValues();
        contentValues.put(mdBhelper.getColNteama(), nteamA);
        contentValues.put(mdBhelper.getColNteamb(), nteamB);
        contentValues.put(mdBhelper.getColScorea(), scA);
        contentValues.put(mdBhelper.getColScoreb(), scB);
        contentValues.put(mdBhelper.getColMatchName(), matchname);
        contentValues.put(mdBhelper.getColDate(), date);
        long insertID = sqLiteDatabase.insert(mdBhelper.getTableFtMatch(), null, contentValues);
        if (insertID>-1){
            return true;
        }else {
            return false;
        }


    }

    public boolean inputMatchBs(String nteamA, String nteamB, int scA , int scB, String date , String matchname){
        tryGetAcces(mdBhelper.getWritableDatabase());
        ContentValues contentValues = new ContentValues();
        contentValues.put(mdBhelper.getColNteama(), nteamA);
        contentValues.put(mdBhelper.getColNteamb(), nteamB);
        contentValues.put(mdBhelper.getColScorea(), scA);
        contentValues.put(mdBhelper.getColScoreb(), scB);
        contentValues.put(mdBhelper.getColMatchName(), matchname);
        contentValues.put(mdBhelper.getColDate(), date);
        long insertID = sqLiteDatabase.insert(mdBhelper.getTableBsMatch(), null, contentValues);
        if (insertID>-1){
            return true;
        }else {
            return false;
        }


    }

    public ArrayList <Match> getAllUserFt(){
        tryGetAcces(mdBhelper.getReadableDatabase());
        String [] col = {mdBhelper.getColMatchid(), mdBhelper.getColNteama(),mdBhelper.getColNteamb() , mdBhelper.getColScorea(),
                mdBhelper.getColScoreb(),mdBhelper.getColDate(), mdBhelper.getColMatchName()};
        Cursor cursor =sqLiteDatabase.query (mdBhelper.getTableFtMatch() , col ,null, null, null
                , null, null);
        ArrayList <Match> m = new ArrayList<>();
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Match match = cursorToMatch(cursor);
                m.add(match);
                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return m;
    }
    public ArrayList <Match> getAllUserBs(){
        tryGetAcces(mdBhelper.getReadableDatabase());
        String [] col = {mdBhelper.getColMatchid(), mdBhelper.getColNteama(),mdBhelper.getColNteamb() , mdBhelper.getColScorea(),
                mdBhelper.getColScoreb(),mdBhelper.getColDate(), mdBhelper.getColMatchName()};
        Cursor cursor =sqLiteDatabase.query (mdBhelper.getTableBsMatch() , col ,null, null, null
                , null, null);
        ArrayList <Match> m = new ArrayList<>();
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Match match = cursorToMatch(cursor);
                m.add(match);
                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return m;
    }

    private Match cursorToMatch(Cursor cursor) {
        Match tempM = new Match();
        tempM.setNteamA(cursor.getString(cursor.getColumnIndex(mdBhelper.getColNteama())));
        tempM.setNteamB(cursor.getString(cursor.getColumnIndex(mdBhelper.getColNteamb())));
        tempM.setDate(cursor.getString(cursor.getColumnIndex(mdBhelper.getColDate())));
        tempM.setMatchname(cursor.getString(cursor.getColumnIndex(mdBhelper.getColMatchName())));
        tempM.setScoreA(cursor.getInt(cursor.getColumnIndex(mdBhelper.getColScorea())));
        tempM.setScoreB(cursor.getInt(cursor.getColumnIndex(mdBhelper.getColScoreb())));
        return tempM;
    }




    private void tryGetAcces (SQLiteDatabase sqLiteDatabase){
        try {
            this.sqLiteDatabase=sqLiteDatabase;
        }catch (SQLiteException e){
            Toast.makeText(context, "gagal dapat acces database", Toast.LENGTH_SHORT).show();
        }
    }
}
