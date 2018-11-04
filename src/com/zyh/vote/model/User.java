package com.zyh.vote.model;

import java.sql.Timestamp;

public class User {


    private int id;

    private String username;

    private String password;

    private String phone;

    private Timestamp create_time;

    private String f_id;

    public User() {
        this.create_time = new Timestamp(System.currentTimeMillis());
        this.f_id = System.currentTimeMillis() + "";
    }

    public User(String username, String password, String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.create_time = new Timestamp(System.currentTimeMillis());
        this.f_id = System.currentTimeMillis() + "";
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", create_time=" + create_time +
                ", f_id='" + f_id + '\'' +
                '}';
    }
}
