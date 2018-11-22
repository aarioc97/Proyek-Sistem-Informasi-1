package com.example.user.prosi_1;

public class User {

    public String id;
    public String userName;
    public String email;
    public String password;
    public String role;

    public User(String id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
