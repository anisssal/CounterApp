package com.example.z.counter.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.z.counter.R;
import com.example.z.counter.adapter.RcAdapt;
import com.example.z.counter.helper.ListHelper;
import com.example.z.counter.model.Match;

import java.util.ArrayList;

/**
 * Created by z on 28/11/17.
 */

public class ListBaketFrag extends Fragment {

    public RecyclerView recyclerView;
    RcAdapt adapt;
    ListHelper listHelper;
    ArrayList<Match> list;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.frag_3,container, false);
        getAllMatch();
        init();
        return v;
    }

    private void getAllMatch() {
        list = new ArrayList<>();

        listHelper = new ListHelper(getActivity().getApplicationContext());
        list.clear();
        list.addAll(listHelper.getAllUserBs());



    }

    private void init() {
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView = v.findViewById(R.id.recybasket);
        adapt = new RcAdapt(getActivity().getApplicationContext(), list, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapt);
    }

}
