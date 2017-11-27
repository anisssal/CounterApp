package com.example.z.counter.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by z on 23/11/17.
 */

public class DBhelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ScoreCounter.db";

    // User table name
    private static final String TABLE_USER = "user";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_PASSWORD = "user_password";


    public static String getTableMatch() {
        return TABLE_MATCH;
    }

    private static final String TABLE_MATCH = "matchtable";

    private static final String COL_MATCHID = "match_id";

    public static String getColMatchid() {
        return COL_MATCHID;
    }

    private static final String COL_NTEAMA= "timAnama";
    private static final String COL_NTEAMB = "tumBnama";
    private static final String COL_SCOREA = "scorea";
    private static final String COL_SCOREB = "scoreb";
    private static final String COL_DATE= "date";



    public static String getColNteama() {
        return COL_NTEAMA;
    }

    public static String getColNteamb() {
        return COL_NTEAMB;
    }

    public static String getColScorea() {
        return COL_SCOREA;
    }

    public static String getColScoreb() {
        return COL_SCOREB;
    }

    public static String getColDate() {
        return COL_DATE;
    }


    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT" + ")";
        String CREATE_MATCH_TABLE = "CREATE TABLE "+TABLE_MATCH+" ("
                +COL_MATCHID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COL_NTEAMA+" TEXT, "
                +COL_NTEAMB+" TEXT, "
                +COL_SCOREA+" INTEGER,"
                +COL_SCOREB+" INTEGER,"
                +COL_DATE+" TEXT)";




        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_MATCH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public static String getTableUser() {
        return TABLE_USER;
    }

    public static String getColumnUserId() {
        return COLUMN_USER_ID;
    }

    public static String getColumnUserName() {
        return COLUMN_USER_NAME;
    }

    public static String getColumnUserPassword() {
        return COLUMN_USER_PASSWORD;
    }
}
