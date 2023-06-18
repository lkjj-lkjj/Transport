package com.example.auth_service.entity;

public class User {
    private int userid;
    private String username;
    private String password;
    private int auth;

    public User(){

    }
    public User(int userid, String username, String password){
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + '\'' + userid + '\'' +
                ", username=" + '\'' + username + '\'' +
                ",password=" + '\'' + password + '\'' +
                ",auth=" + '\'' + auth + '\'' +
                '}';
    }

}
