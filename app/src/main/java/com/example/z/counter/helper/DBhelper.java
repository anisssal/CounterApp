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
    private static final String COLUMN_NAME = "nama";
    private static final String COLUMN_EMAIL= "email";
    private static final String COLUMN_PHONE= "pnumber";
    private static final String COLUMN_IMAGE = "image";

    public static String getColumnName() {
        return COLUMN_NAME;
    }

    public static String getColumnEmail() {
        return COLUMN_EMAIL;
    }

    public static String getColumnPhone() {
        return COLUMN_PHONE;
    }

    public static String getColumnImage() {
        return COLUMN_IMAGE;
    }

    public static String getTableFtMatch() {
        return TABLE_FT_MATCH;
    }

    private static final String TABLE_FT_MATCH = "matchfttable";
    private static final String TABLE_BS_MATCH = "matchbstable";

    public static String getTableBsMatch() {
        return TABLE_BS_MATCH;
    }

    private static final String COL_MATCHID = "match_id";

    public static String getColMatchid() {
        return COL_MATCHID;
    }

    private static final String COL_NTEAMA= "timAnama";
    private static final String COL_NTEAMB = "timBnama";
    private static final String COL_MATCH_NAME = "matchname";

    public static String getColMatchName() {
        return COL_MATCH_NAME;
    }

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
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                 + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT,"
                 +COLUMN_IMAGE+" BLOB NULL, "
                +COLUMN_NAME+" TEXT NULL,"
                +COLUMN_EMAIL+" TEXT NULL,"
                +COLUMN_PHONE+" TEXT NULL"+   ")";


        String CREATE_MATCH_FT_TABLE = "CREATE TABLE "+ TABLE_FT_MATCH +" ("
                +COL_MATCHID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COL_MATCH_NAME+" TEXT,"
                +COL_NTEAMA+" TEXT, "
                +COL_NTEAMB+" TEXT, "
                +COL_SCOREA+" INTEGER,"
                +COL_SCOREB+" INTEGER,"
                +COL_DATE+" TEXT)";

        String CREATE_MATCH_BS_TABLE = "CREATE TABLE "+ TABLE_BS_MATCH+" ("
                +COL_MATCHID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COL_MATCH_NAME+" TEXT,"
                +COL_NTEAMA+" TEXT, "
                +COL_NTEAMB+" TEXT, "
                +COL_SCOREA+" INTEGER,"
                +COL_SCOREB+" INTEGER,"
                +COL_DATE+" TEXT)";




        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_MATCH_FT_TABLE);
        db.execSQL(CREATE_MATCH_BS_TABLE);
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
