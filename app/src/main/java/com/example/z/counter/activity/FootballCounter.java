package com.example.z.counter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z.counter.R;
import com.example.z.counter.helper.ListHelper;

public class FootballCounter extends AppCompatActivity implements View.OnClickListener {



    AlertDialog.Builder builder ;
    AlertDialog alertDialog;
    View v ;

    RelativeLayout editName_a, editName_b;
    EditText edtTeamNm;
    Button btnok, btncancel;
    TextView teamA , teamB;
    TextView scoreA, scoreB;
    Button plusScoreA, plusScoreB, btnSave;
    Toolbar toolbar;
    String tgl , matchname;
    Bundle bundle;

    Button btnResult;


    //objeck sql
    ListHelper listHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_counter);
        initView();
        initobjeck();
        initListener();
        getBundle();
    }

    private void initListener() {

        plusScoreB.setOnClickListener(this);
        plusScoreA.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        editName_a.setOnClickListener(this);
        editName_b.setOnClickListener(this);

    }

    private void initobjeck() {
        builder = new AlertDialog.Builder(FootballCounter.this);
        listHelper = new ListHelper(FootballCounter.this);
    }

    private void getBundle() {
        bundle = getIntent().getExtras();
        try{
            teamA.setText(bundle.getString("nteamA"));
            teamB.setText(bundle.getString("nteamB"));
            tgl= bundle.getString("tgl");
            matchname= bundle.getString("matchnm");

        }catch (Error error){
            Toast.makeText(this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(getApplicationContext(), R.style.ActionBarTitle);
        btnSave = findViewById(R.id.btnSave);
        editName_a = findViewById(R.id.relativeTeamA);
        editName_b = findViewById(R.id.relativeTeamB);
        teamA = findViewById(R.id.teksTeamA);
        teamB = findViewById(R.id.teksTeamB);
        scoreA = findViewById(R.id.score_a);
        scoreB = findViewById(R.id.score_b);
        plusScoreA = findViewById(R.id.goalA);
        plusScoreB = findViewById(R.id.goalB);
        btnResult = findViewById(R.id.btnReset);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goalA:
                setScore(scoreA);
                break;
            case R.id.goalB:
                setScore(scoreB);
                break;
            case R.id.btnSave:
                listHelper.inputMatchFt(teamA.getText().toString(), teamB.getText().toString(),
                        Integer.parseInt(scoreA.getText().toString()), Integer.parseInt(scoreB.getText().toString()),
                        tgl, matchname);
                startActivity(new Intent(FootballCounter.this, MainActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                finish();
                break;
            case R.id.relativeTeamA:
                setAlertDialog(teamA);

                break;
            case R.id.relativeTeamB:
                setAlertDialog(teamB);

                break;
            case R.id.btnReset:
                resetText();
                break;


        }
    }

    private void setScore(TextView score) {

        int a = Integer.parseInt((score.getText().toString()));
        if (a<99){
         a=1+a;
        }else {
            a=a+0;
            Toast.makeText(this, "Score telah mencapai batas maksimal", Toast.LENGTH_LONG).show();
        }
        score.setText(a+"");

    }

    private void resetText(){
        scoreA.setText(0+"");
        scoreB.setText(0+"");

    }

    private void setAlertDialog(final TextView textView){

        v=getLayoutInflater().inflate(R.layout.editteamnm, null,false);
        edtTeamNm = v.findViewById(R.id.editTeamNm);
        btnok=v.findViewById(R.id.btnok);
        btncancel=v.findViewById(R.id.btncancel);
        edtTeamNm.setText(textView.getText().toString());
        builder.setView(v);
        alertDialog=builder.create();
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(edtTeamNm.getText().toString());
                alertDialog.dismiss();
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

       alertDialog.show();
    }


}
