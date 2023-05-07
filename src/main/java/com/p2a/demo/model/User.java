package com.p2a.demo.model;

public class User {
    long id;
    String name;
    String email;
    public User(long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public void setId(long id){
        this.id = id;
    }
}
