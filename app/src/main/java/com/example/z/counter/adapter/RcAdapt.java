package com.example.z.counter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.z.counter.R;
import com.example.z.counter.model.Match;

import java.util.ArrayList;

/**
 * Created by z on 27/11/17.
 */

public class RcAdapt extends RecyclerView.Adapter <RcAdapt.RcHolder>{
    Context context;
    ArrayList <Match> list ;


    public RcAdapt(Context context, ArrayList<Match> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RcAdapt.RcHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlist, parent, false);
        RcHolder holder = new RcHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RcAdapt.RcHolder holder, int position) {
            holder.date.setText(list.get(position).getDate());
            holder.team1.setText(list.get(position).getNteamA());
            holder.team2.setText(list.get(position).getNteamB());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     class RcHolder extends RecyclerView.ViewHolder{


        TextView date , team1 , team2;
        public RcHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.txtDate);
            team1 = itemView.findViewById(R.id.tteama);
            team2  = itemView.findViewById(R.id.tteamb);
        }
    }
}
