package com.example.z.counter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.z.counter.R;
import com.example.z.counter.helper.ListHelper;

public class FootballCounter extends AppCompatActivity implements View.OnClickListener {


    RelativeLayout editName_a, editName_b;
    TextView teamA , teamB;
    TextView scoreA, scoreB;
    Button plusScoreA, plusScoreB, btnSave;
    Toolbar toolbar;
    String tgl;
    Bundle bundle;

    //objeck sql
    ListHelper listHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_counter);
        initView();
        initobjeck();
        getBundle();
    }

    private void initobjeck() {
        listHelper = new ListHelper(FootballCounter.this);
    }

    private void getBundle() {
        bundle = getIntent().getExtras();
        teamA.setText(bundle.getString("nteamA"));
        teamB.setText(bundle.getString("nteamB"));
        tgl= bundle.getString("tgl");
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
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

        plusScoreB.setOnClickListener(this);
        plusScoreA.setOnClickListener(this);
        btnSave.setOnClickListener(this);


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
                listHelper.inputMatch(teamA.getText().toString(), teamB.getText().toString(),
                        Integer.parseInt(scoreA.getText().toString()), Integer.parseInt(scoreB.getText().toString()), tgl);
                startActivity(new Intent(FootballCounter.this, ListMatch_Activity.class));
                finish();
                break;



        }
    }

    private void setScore(TextView score) {
        int a = Integer.parseInt((score.getText().toString())) + 1;
        score.setText(a+"");


    }
}
