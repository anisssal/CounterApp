package com.example.z.counter.model;

/**
 * Created by z on 23/11/17.
 */

public class User {
    int id ;
    String nama, password;

    public User() {
    }

    public User(String nama, String password) {
        this.nama = nama;
        this.password = password;
    }

    public User(int id, String nama, String password) {
        this.id = id;
        this.nama = nama;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
