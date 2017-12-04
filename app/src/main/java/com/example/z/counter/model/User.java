package com.example.z.counter.model;

/**
 * Created by z on 23/11/17.
 */

public class User {
    private byte[] bytes;
    private int id ;
    private String usernama;
    private String password;
    private String nama;
    private String email;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    private String phonenumber;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }



    public User() {
    }

    public User(String usernama, String password) {
        this.usernama = usernama;
        this.password = password;
    }

    public User(int id, String usernama, String password) {
        this.id = id;
        this.usernama = usernama;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsernama() {
        return usernama;
    }

    public void setUsernama(String usernama) {
        this.usernama = usernama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
