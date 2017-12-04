package com.example.z.counter.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.z.counter.DateFragment;
import com.example.z.counter.R;

public class PertandinganBaru extends AppCompatActivity implements View.OnClickListener {




    Button btnstart;

    private Toolbar toolbar;
    private EditText edtTeamA, edtTeamB, edtMatchName;
    @SuppressLint("StaticFieldLeak")
    public static EditText edtDate;

    private CheckBox cbFb ,cbBb;
    boolean jnsP;
    Intent i ;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pertandinganbaru);
        initView();
        initObeck();
        initListener();
        logikClass();

    }

    private void logikClass() {





    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);

        cbFb = findViewById(R.id.cbFootball);
        cbBb = findViewById(R.id.cbBasket);

        edtMatchName =findViewById(R.id.edtNamaPrt);
        edtTeamA = findViewById(R.id.edtTeamA);
        edtTeamB = findViewById(R.id.edtTeamB);
        btnstart=findViewById(R.id.btnstartc);
        edtDate=findViewById(R.id.edtDate);
        //inflate



    }
    private Bundle masukanBundle() {
        bundle.putString("matchnm",edtMatchName.getText().toString().trim() );
        bundle.putString("nteamA", edtTeamA.getText().toString().trim());
        bundle.putString("nteamB", edtTeamB.getText().toString().trim());
        bundle.putString("tgl", edtDate.getText().toString().trim());
        return bundle;
    }




    public void initObeck() {
        bundle = new Bundle() ;
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.mdtp_white));
        toolbar.setTitleTextAppearance(getApplicationContext(), R.style.ActionBarTitle);
       


    }

    public void initListener() {

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
/*

    private void progresDialog(String pesan) {
        progressDialog = new ProgressDialog(this, R.style.themeProgress);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(pesan);
        progressDialog.show();
    }
*/




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }






}


