package com.example.z.counter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    boolean img;


    public RcAdapt(Context context, ArrayList<Match> list, boolean img) {
        this.context = context;
        this.list = list;
        this.img=img;
    }

    @Override
    public RcAdapt.RcHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlist, parent, false);
        RcHolder holder = new RcHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RcAdapt.RcHolder holder, int position) {
            holder.team1.setText(list.get(position).getNteamA());
            holder.team2.setText(list.get(position).getNteamB());
            holder.sc1.setText(String.valueOf(list.get(position).getScoreA()));
            holder.sc2.setText(String.valueOf(list.get(position).getScoreB()));
            if (list.get(position).getMatchname().equals("")){

                holder.matchname.setText("Persahabatan");

            }else {
                holder.matchname.setText(list.get(position).getMatchname());

            }

        if (list.get(position).getDate().equals("")){

            holder.date.setText("Date");

        }else {
            holder.date.setText(list.get(position).getDate());

        }
        if (img){
            holder.imgbola.setImageResource(R.drawable.soccer);
        }else {
            holder.imgbola.setImageResource(R.drawable.ic_basketball);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     class RcHolder extends RecyclerView.ViewHolder{


        TextView date , team1 , team2 , sc1, sc2 , matchname;
        ImageView imgbola;
        public RcHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.txtDate);
            team1 = itemView.findViewById(R.id.tteama);
            team2  = itemView.findViewById(R.id.tteamb);
            sc1= itemView.findViewById(R.id.scteama);
            sc2 = itemView.findViewById(R.id.scteamb);
            matchname = itemView.findViewById(R.id.txtNamaPertandingan);
            imgbola=itemView.findViewById(R.id.imgbola);

        }
    }


}
