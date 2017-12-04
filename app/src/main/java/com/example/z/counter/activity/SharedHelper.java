package com.example.z.counter.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by z on 30/11/17.
 */

public class SharedHelper extends AppCompatActivity{

    private static final String KEYLOGIN = "loginpref";
    private static final String KEYID = "idpref";
    private static final String KEYPASS = "passpref";
    SharedPreferences prefen ;

    public SharedHelper(Context context) {
        prefen = PreferenceManager.getDefaultSharedPreferences(context);

    }

    public void masukanBooleanLoginKeSP(boolean ceklogin) {
        SharedPreferences.Editor editor = prefen.edit();
        editor.putBoolean(KEYLOGIN, ceklogin);
        editor.apply();
    }


    public boolean getSP(){
        boolean login= prefen.getBoolean(KEYLOGIN,false);
        return login;
    };

    public void masukanDataAwalUser(long id, String pass){
        SharedPreferences.Editor editor = prefen.edit();
        editor.putLong(KEYID, id);
        editor.putString(KEYPASS, pass);
        editor.apply();
    }

    public long getID(){
        long id = prefen.getLong(KEYID, 0);
        return id;
    }
    public String getPass(){
        String pass= prefen.getString(KEYPASS, "");
        return pass;
    }



}
