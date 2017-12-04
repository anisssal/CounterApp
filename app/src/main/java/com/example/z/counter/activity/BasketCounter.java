package com.example.z.counter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z.counter.R;
import com.example.z.counter.helper.ListHelper;

public class BasketCounter extends AppCompatActivity implements View.OnClickListener{



    AlertDialog.Builder builder ;
    AlertDialog alertDialog;
    View v ;
    EditText edtTeamNm;
    Button btnok, btncancel;

    ListHelper listHelper;


    RelativeLayout editName_a, editName_b;

    TextView scoreA, scoreB, teamA , teamB;
    Button plus1a, plus1b , plus2a, plus2b, plus3a, plus3b ,btnSave, btnResult;
    Bundle bundle;
    String tgl, matchname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket__counter);
        initView();
        initObjeck();
        initListener();
        getBundle();
    }

    private void initObjeck() {
        builder = new AlertDialog.Builder(BasketCounter.this);
        listHelper = new ListHelper(BasketCounter.this);


    }

    private void initListener() {
        plus2b.setOnClickListener(this);
    plus2a.setOnClickListener(this);
    plus3b.setOnClickListener(this);
    plus3a.setOnClickListener(this);
        plus1a.setOnClickListener(this);
     plus1b.setOnClickListener(this);
        editName_a.setOnClickListener(this);
        editName_b.setOnClickListener(this);
        btnSave.setOnClickListener(this);

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
        teamA = findViewById(R.id.teksTeamA);
        teamB = findViewById(R.id.teksTeamB);
        editName_a = findViewById(R.id.relativeTeamA);
        editName_b = findViewById(R.id.relativeTeamB);
        scoreA = findViewById(R.id.score_a);
        scoreB = findViewById(R.id.score_b);
        btnSave = findViewById(R.id.btnSave);

        plus1a = findViewById(R.id.plus1a);
        plus1b= findViewById(R.id.plus1b);
        plus2a = findViewById(R.id.plus2a);
        plus2b =findViewById(R.id.plus2b);
        plus3a = findViewById(R.id.plus3a);
        plus3b = findViewById(R.id.plus3b);
        btnResult = findViewById(R.id.btnReset);

    }

    private void setScore(TextView score , int sc) {
        int a = Integer.parseInt((score.getText().toString())) ;
        if (a<99){

            a=sc+a;
        }else {
            a=a+0;
            Toast.makeText(this, "Score telah mencapai batas maksimal", Toast.LENGTH_LONG).show();
        }
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
            case R.id.relativeTeamA:
                setAlertDialog(teamA);

                break;
            case R.id.relativeTeamB:
                setAlertDialog(teamB);

                break;
            case R.id.btnSave:
                listHelper.inputMatchBs(teamA.getText().toString(), teamB.getText().toString(),
                        Integer.parseInt(scoreA.getText().toString()), Integer.parseInt(scoreB.getText().toString()),
                        tgl, matchname);
                startActivity(new Intent(BasketCounter.this, MainActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
                break;
            case R.id.btnReset:
                resetText();
                break;

        }
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

    private void resetText(){
        scoreA.setText(0+"");
        scoreB.setText(0+"");

    }
}
