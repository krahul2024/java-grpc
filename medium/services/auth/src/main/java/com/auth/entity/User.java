package com.auth.entity;

public class User {
    private Integer id;
    private String name;
    private String userName;
    private String password;

    public User (Integer id, String name, String userName, String password) {
        this.id       = id;
        this.name     = name;
        this.userName = userName;
        this.password = password;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getId () {
        return this.id;
    }

    public String getName () {
        return this.name;
    }

    public String getUserName () {
        return this.userName;
    }
    public String getPassword() {
        return this.password;
    }
}
