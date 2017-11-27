package com.example.z.counter.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z.counter.R;

public class BasketCounter extends AppCompatActivity implements View.OnClickListener{


    TextView teamA , teamB;
    TextView scoreA, scoreB;
    Button plus1a, plus1b , plus2a, plus2b, plus3a, plus3b;
    Bundle bundle;
    String tgl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket__counter);
        initView();
        initListener();
        getBundle();
    }

    private void initListener() {
        plus2b.setOnClickListener(this);
plus2a.setOnClickListener(this);
    plus3b.setOnClickListener(this);
    plus3a.setOnClickListener(this);
    plus1a.setOnClickListener(this);
    plus1b.setOnClickListener(this);

    }

    private void getBundle() {
        bundle = getIntent().getExtras();
        try{
            teamA.setText(bundle.getString("nteamA"));
            teamB.setText(bundle.getString("nteamB"));
            tgl= bundle.getString("tgl");
        }catch (Error error){
            Toast.makeText(this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private void initView() {
        teamA = findViewById(R.id.teksTeamA);
        teamB = findViewById(R.id.teksTeamB);
        scoreA = findViewById(R.id.score_a);
        scoreB = findViewById(R.id.score_b);
        plus1a = findViewById(R.id.plus1a);
        plus1b= findViewById(R.id.plus1b);
        plus2a = findViewById(R.id.plus2a);
        plus2b =findViewById(R.id.plus2b);
        plus3a = findViewById(R.id.plus3a);
        plus3b = findViewById(R.id.plus3b);
    }

    private void setScore(TextView score , int sc) {
        int a = Integer.parseInt((score.getText().toString())) + sc;
        score.setText(a + "");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plus1a:
                setScore(scoreA, 1);
                break;
            case R.id.plus1b:
            setScore(scoreB, 1);
                break;
            case R.id.plus2a:
            setScore(scoreA, 2);
                break;
                case R.id.plus2b:
            setScore(scoreB, 2);
                break;
            case R.id.plus3a:
                setScore(scoreA, 3);
                break;
            case R.id.plus3b:
                setScore(scoreB, 3);
                break;

        }
    }
}
