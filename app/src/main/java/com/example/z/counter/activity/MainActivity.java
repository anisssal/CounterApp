package com.example.z.counter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.z.counter.R;
import com.example.z.counter.adapter.ViewPagerAdapter;
import com.example.z.counter.fragment.ListBaketFrag;
import com.example.z.counter.fragment.ListFootBallFrag;
import com.example.z.counter.fragment.Tab1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , TabLayout.OnTabSelectedListener{


    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    FloatingActionButton fab ;
    //prefences
   SharedHelper sharedHelper;

   //bundle
   Bundle bundle;

    long id;
    String pass, user;
    Tab1 tab1;
    boolean satu;


    int positionTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initObjeck();
        initListener();
        setupToolbar(toolbar);
        getBundle();

        setupViewPager(viewPager ,viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    public String getPass() {
        return pass;
    }

    public String getUser() {
        return user;
    }

    private void getBundle() {
        bundle=getIntent().getExtras();
        if (bundle!=null ){
            id= bundle.getLong("idnya");
            pass=bundle.getString("pass");
            satu=bundle.getBoolean("satu");
            user=bundle.getString("user");

        }
    }

    private void initObjeck() {
        tab1= new Tab1();
        sharedHelper= new SharedHelper(getApplicationContext());
    }

    public boolean getSatu() {
        return satu;
    }

    private void initListener() {
        fab.setOnClickListener(this);
        tabLayout.setOnTabSelectedListener(this);

    }

    private void initView() {
        toolbar=findViewById(R.id.toolbar);
        tabLayout= findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewPager);
        fab = findViewById(R.id.fab);

    }


    private void setupToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(getApplicationContext(), R.style.ActionBarTitle);


    }

    private void setupViewPager(ViewPager viewPager , ViewPagerAdapter viewPagerAdapter){
        viewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(tab1," Account");
        viewPagerAdapter.addFragment(new ListFootBallFrag(), " Football");
        viewPagerAdapter.addFragment(new ListBaketFrag(), " Basketball");
        viewPager.setAdapter(viewPagerAdapter);
//        tabLayout.getTabAt(1).select();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                if(positionTab==0){
                    sharedHelper.masukanBooleanLoginKeSP(false);
                    Intent i = new Intent(MainActivity.this, SplashActivity.class);
                    finish();
                startActivity(i);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }else {
                    startActivity(new Intent(MainActivity.this, PertandinganBaru.class));
                    finish();

                }
        }
    }



    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        positionTab= tab.getPosition();
        if (positionTab==0){
            fab.setImageResource(R.drawable.ic_logout);
        }else {
            fab.setImageResource(R.drawable.ic_add_black_24dp);

        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    public long getId() {
        return id;
    }
}
