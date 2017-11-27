package com.example.z.counter.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.z.counter.R;

public class SplashActivity extends AppCompatActivity {


    private static final String KEYLOGIN = "login";
    SharedPreferences prefen ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initObjeck();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, PertandinganBaru.class));
            }
        }, 3000);
    }

    private void initObjeck() {
        prefen = getPreferences( MODE_PRIVATE);
    }
    public void cekSP() {
        if (prefen.contains(KEYLOGIN) == true) {
            PertandinganBaru.alertDialog.dismiss();
        }
    }
}
