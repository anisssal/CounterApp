package com.example.z.counter.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.z.counter.R;
import com.example.z.counter.adapter.RcAdapt;
import com.example.z.counter.helper.ListHelper;
import com.example.z.counter.model.Match;

import java.util.ArrayList;

public class ListMatch_Activity extends AppCompatActivity {


    RecyclerView recyclerView;
    RcAdapt adapt;
    ListHelper listHelper;
    ArrayList <Match> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_match_);
        getAllMatch();
        init();

    }

    private void getAllMatch() {
        list = new ArrayList<>();

        listHelper = new ListHelper(getApplicationContext());
        list.clear();
        list.addAll(listHelper.getAllUser());
    }

    private void init() {
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView = findViewById(R.id.recy);
    adapt = new RcAdapt(getApplicationContext(), list);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapt);
    }
}
