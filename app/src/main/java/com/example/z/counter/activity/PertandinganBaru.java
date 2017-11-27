package com.example.z.counter.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z.counter.DateFragment;
import com.example.z.counter.LoginInputValidation;
import com.example.z.counter.R;
import com.example.z.counter.helper.Userhelper;
import com.example.z.counter.model.User;

public class PertandinganBaru extends AppCompatActivity implements View.OnClickListener {

    //shared prefens
    private static final String KEYLOGIN = "login";
    public static AlertDialog alertDialog;


    //inflater
    AlertDialog.Builder builder;
    LayoutInflater inflater;
    View v;
    TextView login, signup;
    LinearLayout linearLayout;
    Button btnlogin, btnsignup, btnstart;
    EditText edtPass, edtPass2, edtUser;
    //databaselogin
    User user;
    Userhelper userhelper;
    LoginInputValidation loginInputValidation;
    ProgressDialog progressDialog;
    boolean logined;
    SharedPreferences prefen;



    private Toolbar toolbar;
    private EditText edtTeamA, edtTeamB;
    @SuppressLint("StaticFieldLeak")
    public static EditText edtDate;

    private CheckBox cbFb ,cbBb;
    boolean jnsP;
    Intent i ;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertandingan_baru);
        initView();
        initObeck();
        initAlertLogin();
        initListener();
        cekSP();
        logikClass();

    }

    private void logikClass() {





    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);

        cbFb = findViewById(R.id.cbFootball);
        cbBb = findViewById(R.id.cbBasket);

        edtDate = findViewById(R.id.edtDate);
        edtTeamA = findViewById(R.id.edtTeamA);
        edtTeamB = findViewById(R.id.edtTeamB);
        btnstart=findViewById(R.id.btnstartc);

        //inflate


        linearLayout = findViewById(R.id.pertandbaru);
        inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.login, null);
        edtUser = v.findViewById(R.id.inUser);
        edtPass = v.findViewById(R.id.inPass);
        edtPass2 = v.findViewById(R.id.inPass2);
        login = v.findViewById(R.id.idtext_signIn);
        signup = v.findViewById(R.id.idtext_signUP);
        btnlogin = v.findViewById(R.id.btnLogin);
        btnsignup = v.findViewById(R.id.btnsignup);


    }
    private Bundle masukanBundle() {

        bundle.putString("nteamA", edtTeamA.getText().toString().trim());
        bundle.putString("nteamB", edtTeamB.getText().toString().trim());
        bundle.putString("tgl", edtDate.getText().toString().trim());
        return bundle;
    }


    public void initAlertLogin() {

        builder = new AlertDialog.Builder(PertandinganBaru.this);
        builder.setView(v);
        builder.setCancelable(false);
        alertDialog = builder.show();
    }

    public void initObeck() {
        bundle = new Bundle() ;
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(getApplicationContext(), R.style.ActionBarTitle);
        userhelper = new Userhelper(this);
        loginInputValidation = new LoginInputValidation(this);
        prefen = getPreferences(MODE_PRIVATE);

    }

    public void initListener() {
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        btnsignup.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        edtDate.setOnClickListener(this);
        btnstart.setOnClickListener(this);
        cbFb.setOnClickListener(this);
        cbBb.setOnClickListener(this);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveIconBar:

        }
        return super.onOptionsItemSelected(item);
    }





    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.idtext_signIn:
                loginkeliatan();

                break;
            case R.id.idtext_signUP:

                signupkeliatan();
                break;
            case R.id.btnsignup:
                Toast.makeText(this, "btndipencet", Toast.LENGTH_SHORT).show();
                if (!validasiData()) {

                    return;
                } else {

                    user = new User(edtUser.getText().toString().trim(), edtPass2.getText().toString());
                    userhelper.inputNewUser(user);
                    loginkeliatan();
                    progresDialog("Registering... ");
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            progressDialog.dismiss();
                            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        }
                    }, 2000);
                }
                break;
            case R.id.btnLogin:
                final String namaUser = edtUser.getText().toString();
                if (!loginInputValidation.inputTextFilled(edtUser, "Masukkan User")) {
                    return;
                }
                if (!loginInputValidation.inputTextFilled(edtPass, "Masukkan password ")) {
                    return;
                }
                if (userhelper.loginUserCheck(edtUser.getText().toString(), edtPass.getText().toString())) {
                    refreshView();
                    logined = true;
                    masukanKeSP(logined);
                    progresDialog("Authenticating... ");
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            progressDialog.dismiss();

                            alertDialog.dismiss();
                            Snackbar.make(linearLayout, "  Sukses Login\n    Wellcome " + namaUser, Snackbar.LENGTH_LONG).setAction("Ok", null).show();
                        }
                    }, 2000);

                } else {
                    refreshView();
                    Snackbar.make(v, "Nama atau Password salah", Snackbar.LENGTH_SHORT).show();
                }
                break;
            case R.id.edtDate:
                DateFragment dateFragment = new DateFragment();
                dateFragment.show(getSupportFragmentManager(), "fragmentdate");
                break;
            case R.id.btnstartc:
                if (cbFb.isChecked()){
                    i= new Intent(PertandinganBaru.this, FootballCounter.class);
                }else {
                    i= new Intent(PertandinganBaru.this, BasketCounter.class);
                }
                Bundle bundle = masukanBundle();
                i.putExtras(bundle);
                startActivity(i);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
                break;

            case R.id.cbFootball:
                cbBb.setChecked(false);

                break;
            case R.id.cbBasket:
                cbFb.setChecked(false);

                break;

        }
    }

    private void progresDialog(String pesan) {
        progressDialog = new ProgressDialog(this, R.style.themeProgress);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(pesan);
        progressDialog.show();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    public void refreshView() {
        loginInputValidation.hideKeyboardFrom(edtUser);
        loginInputValidation.hideKeyboardFrom(edtPass);
        edtUser.setText(null);
        edtPass.setText(null);
        edtPass2.setText(null);
    }

    public void loginkeliatan() {
        signup.setBackgroundResource(R.color.abuminopa);
        login.setBackgroundResource(android.R.color.white);
        btnlogin.setVisibility(View.VISIBLE);
        btnsignup.setVisibility(View.GONE);
        edtPass2.setVisibility(View.GONE);
        refreshView();
    }

    public void signupkeliatan() {
        login.setBackgroundResource(R.color.abuminopa);
        signup.setBackgroundResource(android.R.color.white);
        edtPass2.setVisibility(View.VISIBLE);
        btnsignup.setVisibility(View.VISIBLE);
        btnlogin.setVisibility(View.GONE);
        refreshView();
    }

    public void masukanKeSP(boolean ceklogin) {
        SharedPreferences.Editor editor = prefen.edit();
        editor.putBoolean(KEYLOGIN, ceklogin);
        editor.apply();
    }

    public void cekSP() {
        if (prefen.contains(KEYLOGIN) == true) {
            alertDialog.dismiss();
        }
    }

}


