package com.example.z.counter.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.z.counter.LoginInputValidation;
import com.example.z.counter.R;
import com.example.z.counter.helper.Userhelper;
import com.example.z.counter.model.User;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {


    Userhelper userhelper;
    public AlertDialog alertDialog;
    RelativeLayout relatip;

    ProgressBar progressBar;
    Button btnsignin, btnsingup;
    TextView already;
    Activity activity;

    //database login
    LoginInputValidation loginInputValidation;
    User user;

    //inflater
    AlertDialog.Builder builder;
    LayoutInflater inflater;
    View v;
    TextView txtinflogin, txtinfsignup;
    Button btninflogin, btninfsignup;
    EditText edtPass, edtPass2, edtUser;

    //prefen
    SharedHelper sharedHelper;
    //handler
    Handler handler;
    Runnable runnable;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initObjeck();
        initAlertLogin();
        initListener();
        animate();
        cekSP();




    }

    private void loading(){
       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);

            }
        }, 2000);
    }

    private void initListener() {
        btnsignin.setOnClickListener(this);
        btnsingup.setOnClickListener(this);
        txtinflogin.setOnClickListener(this);
        txtinfsignup.setOnClickListener(this);
        btninflogin.setOnClickListener(this);
        btninfsignup.setOnClickListener(this);

    }


    public void initAlertLogin() {

        builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setView(v);
        builder.setCancelable(true);
        alertDialog = builder.create();
    }

    private void initView() {
        already = findViewById(R.id.txthaveanan);
        progressBar = findViewById(R.id.progressBar);
        btnsingup = findViewById(R.id.btnsignup);
        btnsignin = findViewById(R.id.btnsignin);
        relatip = findViewById(R.id.relatip);


        //inflate


        inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.login, null);
        edtUser = v.findViewById(R.id.inUser);
        edtPass = v.findViewById(R.id.inPass);
        edtPass2 = v.findViewById(R.id.inPass2);
        txtinflogin = v.findViewById(R.id.idtext_signIn);
        txtinfsignup = v.findViewById(R.id.idtext_signUP);
        btninflogin = v.findViewById(R.id.btninfLogin);
        btninfsignup = v.findViewById(R.id.btninfsignup);

    }

    private void initObjeck() {
        activity=SplashActivity.this;
        loginInputValidation = new LoginInputValidation(this);
        userhelper = new Userhelper(this);
        sharedHelper=new SharedHelper(getApplicationContext());
    }

    public void cekSP() {
        if (sharedHelper.getSP()== true) {
            loading();
            handler=new Handler();
            runnable= new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class ));
                    finish();

                }
            };
            handler.postDelayed(runnable, 2400);



        }else if (sharedHelper.getSP()==false){
            loading();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idtext_signIn:
                loginkeliatan();
                break;
            case R.id.idtext_signUP:
                signupkeliatan();
                break;
            case R.id.btninfsignup:
                gosignup();
                break;
            case R.id.btninfLogin:
                gologin();
                break;
            case R.id.btnsignup:
                signupkeliatan();
                break;
            case R.id.btnsignin:
                loginkeliatan();
                break;


        }

    }

    private void gologin(){
        if (!loginInputValidation.inputTextFilled(edtUser, "Masukkan User")) {
            return;
        }
        if (!loginInputValidation.inputTextFilled(edtPass, "Masukkan password ")) {
            return;
        }
        if (userhelper.loginUserCheck(edtUser.getText().toString(), edtPass.getText().toString())) {
            sharedHelper.masukanBooleanLoginKeSP(true);
            Intent i =new Intent(activity, MainActivity.class);
            bundle=new Bundle();
            bundle.putLong("idnya", userhelper.getUserId(edtUser.getText().toString(),edtPass.getText().toString()));
//            Log.i("idberhasil", userhelper.getUserId(edtUser.getText().toString(),edtPass.getText().toString())+"");
            bundle.putString("pass", edtPass.getText().toString());
            bundle.putString("user", edtUser.getText().toString());
            bundle.putBoolean("satu", true);
            i.putExtras(bundle);
            startActivity(i);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            finish();
           /* progresDialog("Authenticating... ");
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    progressDialog.dismiss();

                    alertDialog.dismiss();
                    Snackbar.make(linearLayout, "  Sukses Login\n    Wellcome " + namaUser, Snackbar.LENGTH_LONG).setAction("Ok", null).show();
                }
            }, 2000);*/

        } else {
            refreshView();
            Snackbar.make(v, "Nama atau Password salah", Snackbar.LENGTH_SHORT).show();
        }

    }

    private void gosignup() {
        if (!validasiData()) {

            return;
        } else {
            user = new User(edtUser.getText().toString().trim(), edtPass2.getText().toString());
            userhelper.inputNewUser(user);
            Snackbar.make(relatip, "Success sign up \n please Log In", Snackbar.LENGTH_LONG).setAction("Ok", null).show();
            alertDialog.dismiss();
          /*  progresDialog("Registering... ");
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    progressDialog.dismiss();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
            }, 2000);*/
        }


    }


    private void animate() {
        already.animate().alpha(10f).setDuration(2000);
        btnsignin.setTranslationX(-2000);
        btnsingup.setTranslationX(1000);
        btnsignin.animate().translationXBy(2000).setDuration(2000);
        btnsingup.animate().translationXBy(-1000).setDuration(2000);
    }

    public void loginkeliatan() {
        alertDialog.show();
        txtinfsignup.setVisibility(View.GONE);
        txtinflogin.setVisibility(View.VISIBLE);
        btninflogin.setVisibility(View.VISIBLE);
        btninfsignup.setVisibility(View.GONE);
        edtPass2.setVisibility(View.GONE);
        refreshView();
    }

    public void signupkeliatan() {
        alertDialog.show();
        txtinflogin.setVisibility(View.GONE);

        txtinfsignup.setVisibility(View.VISIBLE);
        edtPass2.setVisibility(View.VISIBLE);
        btninfsignup.setVisibility(View.VISIBLE);
        btninflogin.setVisibility(View.GONE);
        refreshView();
    }

    public void refreshView() {
        loginInputValidation.hideKeyboardFrom(edtUser);
        loginInputValidation.hideKeyboardFrom(edtPass);
        edtUser.setText(null);
        edtPass.setText(null);
        edtPass2.setText(null);
    }

    private boolean validasiData() {

        if (!loginInputValidation.inputTextFilled(edtUser, "Masukkan User")) {
            return false;
        }
        if (!loginInputValidation.inputTextFilled(edtPass, "Masukkan password ")) {
            return false;
        }

        if (!loginInputValidation.inputTextFilled(edtPass2, "Masukkan confirm password")) {
            return false;

        }
        if (!loginInputValidation.inputPassMacthes(edtPass, edtPass2)) {

            return false;
        }
        return true;
    }

}
