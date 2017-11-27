package com.example.z.counter.model;

/**
 * Created by z on 27/11/17.
 */

public class Match {
    private String nteamA;
    private String nteamB;
    private int scoreA;
    private int scoreB;
    private String date;

    public String getNteamA() {
        return nteamA;
    }

    public void setNteamA(String nteamA) {
        this.nteamA = nteamA;
    }

    public String getNteamB() {
        return nteamB;
    }

    public void setNteamB(String nteamB) {
        this.nteamB = nteamB;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
